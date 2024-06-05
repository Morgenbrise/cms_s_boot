package com.server.cms.service;

import com.server.cms.data.request.wevtoon.QTqWebtoonPostData;
import com.server.cms.data.request.wevtoon.QWebtoonData.Modify;
import com.server.cms.data.request.wevtoon.QWebtoonData.Search;
import com.server.cms.data.response.webtoon.SCpWebtoon;
import com.server.cms.domain.Contract;
import com.server.cms.domain.CpUser;
import com.server.cms.domain.thumbnail.CpThumbnail;
import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.domain.webtoon.Webtoon;
import com.server.cms.exception.FileData;
import com.server.cms.framework.common.Base64Encoding;
import com.server.cms.framework.common.FileUtil;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.common.Unique;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.framework.file.type.ImageFileEnum;
import com.server.cms.repository.*;
import com.server.cms.repository.file.CpThumbnailRepository;
import com.server.cms.repository.webtoon.TqWebtoonRepository;
import com.server.cms.repository.webtoon.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
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
    private final CpThumbnailRepository cpThumbnailRepository;

    @Value("${directory.upload.content}")
    private String UPLOAD_FILE_DIR;

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

    public SCpWebtoon.Item saveCpWebtoon(QTqWebtoonPostData.Save param, MultipartFile thumbnail) {
        String companyCode = currentCompanyCode();
        log.info("CP_SAVE_USER : {}", companyCode);

        Optional<CpUser> optional = userRepository.findByUserCompanyCode(companyCode);
        CpUser user = optional.orElseThrow(() -> new UserNotFoundException());

        String contentInd = Base64Encoding.encodingInteger(user.getInd());
        String bookCode = "W_" + contentInd + Unique.UniqueNumber();
        log.info("CP_SAVE_BOOK_CODE[{}] : {}", companyCode, bookCode);

        TqWebtoon tempWebtoon = TqWebtoon.create(param);
        TqWebtoon save = tqWebtoonRepository.save(tempWebtoon);
        log.info("CP_SAVE_WEBTOON[{}] : BOOK_CODE ---> {}, IND ---> {}", companyCode, bookCode, save.getInd());

        String path = Paths.get(UPLOAD_FILE_DIR, bookCode).toString();
        FileData fileData = FileUtil.imageFileSave(thumbnail, path, ImageFileEnum.WEBTOON);
        log.info("CP_SAVE_WEBTOON[{}] : BOOK_CODE ---> {}, FILE ---> {}", companyCode, bookCode, fileData.toString());

        CpThumbnail thumbnailEntity = CpThumbnail.create(fileData, save);
        CpThumbnail cpThumbnail = cpThumbnailRepository.save(thumbnailEntity);

        Contract tempContract = new Contract.Builder()
                                .bookCode(bookCode)
                                .tqWebtoon(save)
                                .cpUser(user)
                                .build();

        Contract contract = contractRepository.save(tempContract);
        log.info("CP_SAVE_WEBTOON[{}] : BOOK_CODE ---> {}, FILE ---> {}", companyCode, bookCode, fileData.toString());

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
