package com.server.cms.service;

import com.server.cms.data.request.wevtoon.QTqWebtoonPostData;
import com.server.cms.data.request.wevtoon.QWebtoonData.Modify;
import com.server.cms.data.request.wevtoon.QWebtoonData.Search;
import com.server.cms.data.response.SCpWebtoon;
import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.domain.webtoon.Webtoon;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.common.Unique;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.repository.TqWebtoonRepository;
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

    private final WebtoonRepository webtoonRepository;
    private final TqWebtoonRepository tqWebtoonRepository;

    public ResponsePageDTO findByWebtoons(Long userInd, Search param) {
        if(userInd == null) {
            throw new UserNotFoundException();
        }

        return webtoonRepository.findByWebtoons(userInd, param);
    }

    public SCpWebtoon.Item saveCpWebtoon(QTqWebtoonPostData.Save param) {
        Long userInd = currentUserInd();
        // TODO 이미지 등록 로직 필요
        String bookNum = "W_" + userInd + Unique.UniqueNumber();
        TqWebtoon tqWebtoon = TqWebtoon.create(param);
        TqWebtoon save = tqWebtoonRepository.save(tqWebtoon);
        return SCpWebtoon.Item.form(save);
    }

    public SCpWebtoon.Item saveWebtoon(String bookNum) {
        if(isNotEmpty(bookNum)) {
            throw new ContentNotFoundException("도서코드를 정확하지 않습니다.");
        }

        TqWebtoon tqWebtoon = tqWebtoonRepository.findByCpWebtoon(bookNum);
        Webtoon webtoon = webtoonRepository.save(Webtoon.create(tqWebtoon));

        return SCpWebtoon.Item.form(webtoon);
    }

    public SCpWebtoon.Item modifyCpWebtoon(QTqWebtoonPostData.Modify param) {
        if(param.getInd() == null) {
            throw new IllegalArgumentException("작품 정보가 존재하지 않습니다.");
        }

        Optional<TqWebtoon> optional = tqWebtoonRepository.findById(param.getInd());
        TqWebtoon tqWebtoon = optional.orElseThrow(() -> new ContentNotFoundException("작품 정보가 존재하지 않습니다."));

        tqWebtoon.update(param);
        return SCpWebtoon.Item.form(tqWebtoon);
    }

    public void modifyWebtoon(Modify param) {
        if(param.getInd() == null) {
            throw new ContentNotFoundException("작품 정보가 존재하지 않습니다.");
        }

        Optional<Webtoon> optional = webtoonRepository.findById(param.getInd());
        Webtoon webtoon = optional.orElseThrow(() -> new ContentNotFoundException("작품 정보가 존재하지 않습니다."));

    }

}
