package com.server.cms.service;

import com.server.cms.data.request.wevtoon.QTqWebtoonPostData;
import com.server.cms.data.request.wevtoon.QWebtoonData.Modify;
import com.server.cms.data.request.wevtoon.QWebtoonData.Search;
import com.server.cms.data.response.SCpWebtoon;
import com.server.cms.domain.webtoon.TqWeboon;
import com.server.cms.domain.webtoon.Webtoon;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.repository.TqWebtoonRepository;
import com.server.cms.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        TqWeboon tqWeboon = TqWeboon.create(param);
        // TODO 작품 번호 생성로직 및 이미지 등록 로직 필요
        TqWeboon save = tqWebtoonRepository.save(tqWeboon);
        return SCpWebtoon.Item.form(save);
    }

    public SCpWebtoon.Item modifyCpWebtoon(QTqWebtoonPostData.Modify param) {
        if(param.getInd() == null) {
            throw new IllegalArgumentException("작품 정보가 존재하지 않습니다.");
        }

        Optional<TqWeboon> optional = tqWebtoonRepository.findById(param.getInd());
        TqWeboon tqWeboon = optional.orElseThrow(() -> new ContentNotFoundException("작품 정보가 존재하지 않습니다."));

        tqWeboon.update(param);

        return SCpWebtoon.Item.form(tqWeboon);
    }

    public void modifyWebtoon(Modify param) {
        if(param.getInd() == null) {
            throw new ContentNotFoundException("작품 정보가 존재하지 않습니다.");
        }

        Optional<Webtoon> optional = webtoonRepository.findById(param.getInd());
        Webtoon webtoon = optional.orElseThrow(() -> new ContentNotFoundException("작품 정보가 존재하지 않습니다."));



    }

}
