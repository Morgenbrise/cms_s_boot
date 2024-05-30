package com.server.cms.service;

import com.server.cms.data.request.wevtoon.QTqWebtoonPostData;
import com.server.cms.data.request.wevtoon.QWebtoonData.Modify;
import com.server.cms.data.request.wevtoon.QWebtoonData.Search;
import com.server.cms.data.response.webtoon.SCpWebtoon;
import com.server.cms.domain.Contract;
import com.server.cms.domain.CpUser;
import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.domain.webtoon.Webtoon;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.common.Unique;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.server.cms.config.security.SecurityUtils.currentCompanyCode;
import static com.server.cms.framework.date.LocalDateUtil.getConvertDateTimeToString;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebtoonService {

    private final UserRepository userRepository;
    private final WebtoonRepository webtoonRepository;
    private final TqWebtoonRepository tqWebtoonRepository;
    private final ContractRepository contractRepository;

    public ResponsePageDTO findByWebtoons(String companyCode, Search param) {
        if(StringUtils.isEmpty(companyCode)) {
            throw new UserNotFoundException();
        }

        return webtoonRepository.findByWebtoons(companyCode, param);
    }

    public ResponsePageDTO findByCpWebtoons(String companyCode, Search param) {
        if(StringUtils.isEmpty(companyCode)) {
            throw new UserNotFoundException();
        }

        return tqWebtoonRepository.findByCpWebtoons(companyCode, param);
    }

    public SCpWebtoon.Item findByCpWebtoon(String bookCode) {
        String companyCode = currentCompanyCode();

        TqWebtoon webtoon = tqWebtoonRepository.findByCpWebtoon(companyCode, bookCode);
        return SCpWebtoon.Item.form(bookCode, webtoon);
    }

    public SCpWebtoon.Item saveCpWebtoon(QTqWebtoonPostData.Save param) {
        String companyCode = currentCompanyCode();
        Optional<CpUser> optional = userRepository.findByUserCompanyCode(companyCode);
        CpUser user = optional.orElseThrow(() -> new UserNotFoundException());

        String bookNum = "W_" + user.getInd() + Unique.UniqueNumber();
        TqWebtoon tempWebtoon = TqWebtoon.create(param);
        // TODO 이미지 등록 로직 필요
        TqWebtoon save = tqWebtoonRepository.save(tempWebtoon);

        Contract tempContract = new Contract.Builder()
                                .bookCode(bookNum)
                                .tqWebtoon(save)
                                .cpUser(user)
                                .build();
        Contract contract = contractRepository.save(tempContract);

        return SCpWebtoon.Item.form(contract.getBookCode(), save);
    }

    public SCpWebtoon.Item saveWebtoon(String bookNum) {
        requiredBookCode(bookNum);

        TqWebtoon tqWebtoon = tqWebtoonRepository.findByCpWebtoon(bookNum);
        Webtoon webtoon = webtoonRepository.save(Webtoon.create(tqWebtoon));

        return SCpWebtoon.Item.form(bookNum, webtoon);
    }



    public SCpWebtoon.Item modifyCpWebtoon(QTqWebtoonPostData.Modify param) {
        String bookCode = param.getBookCode();
        requiredBookCode(bookCode);

        TqWebtoon tqWebtoon = tqWebtoonRepository.findByCpWebtoon(bookCode);
        Webtoon webtoon = webtoonRepository.findByWebtoon(bookCode);

        tqWebtoon.update(param, webtoon);
        return SCpWebtoon.Item.form(bookCode, tqWebtoon);
    }

    public void modifyWebtoon(Modify param) {
        if(param.getInd() == null) {
            throw new ContentNotFoundException("작품 정보가 존재하지 않습니다.");
        }

        Optional<Webtoon> optional = webtoonRepository.findById(param.getInd());
        Webtoon webtoon = optional.orElseThrow(() -> new ContentNotFoundException("작품 정보가 존재하지 않습니다."));

    }

    private void requiredBookCode(String bookCode) {
        if(isEmpty(bookCode)) {
            throw new ContentNotFoundException("도서코드를 정확하지 않습니다.");
        }
    }

}
