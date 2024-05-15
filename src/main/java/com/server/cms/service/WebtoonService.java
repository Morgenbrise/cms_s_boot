package com.server.cms.service;

import com.server.cms.data.request.wevtoon.QTqWebtoonPostData;
import com.server.cms.data.request.wevtoon.QWebtoonData.Modify;
import com.server.cms.data.request.wevtoon.QWebtoonData.Search;
import com.server.cms.data.response.SCpWebtoon;
import com.server.cms.domain.Contract;
import com.server.cms.domain.CpUser;
import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.domain.webtoon.Webtoon;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.common.Unique;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.repository.ContractRepository;
import com.server.cms.repository.TqWebtoonRepository;
import com.server.cms.repository.UserRepository;
import com.server.cms.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.server.cms.config.security.SecurityUtils.currentUserInd;
import static com.server.cms.framework.date.LocalDateUtil.getConvertDateTimeToString;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebtoonService {

    private final UserRepository userRepository;
    private final WebtoonRepository webtoonRepository;
    private final TqWebtoonRepository tqWebtoonRepository;
    private final ContractRepository contractRepository;

    public ResponsePageDTO findByWebtoons(Long userInd, Search param) {
        if(userInd == null) {
            throw new UserNotFoundException();
        }

        return webtoonRepository.findByWebtoons(userInd, param);
    }

    public ResponsePageDTO findByCpWebtoons(Long userInd, Search param) {
        if(userInd == null) {
            throw new UserNotFoundException();
        }

        return tqWebtoonRepository.findByCpWebtoons(userInd, param);
    }

    public SCpWebtoon.Item saveCpWebtoon(QTqWebtoonPostData.Save param) {
        Long userInd = currentUserInd();
        Optional<CpUser> optional = userRepository.findById(userInd);
        CpUser user = optional.orElseThrow(() -> new UserNotFoundException());

        String bookNum = "W_" + userInd + Unique.UniqueNumber();
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
        if(isNotEmpty(bookNum)) {
            throw new ContentNotFoundException("도서코드를 정확하지 않습니다.");
        }

        TqWebtoon tqWebtoon = tqWebtoonRepository.findByCpWebtoon(bookNum);
        Webtoon webtoon = webtoonRepository.save(Webtoon.create(tqWebtoon));

        return SCpWebtoon.Item.form(bookNum, webtoon);
    }

    public SCpWebtoon.Item modifyCpWebtoon(QTqWebtoonPostData.Modify param) {
        String bookCode = param.getBookCode();
        if(bookCode == null) {
            throw new IllegalArgumentException("작품 정보가 존재하지 않습니다.");
        }

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

}
