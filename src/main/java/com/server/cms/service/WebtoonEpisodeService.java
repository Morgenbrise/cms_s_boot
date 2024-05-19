package com.server.cms.service;

import com.server.cms.data.request.wevtoon.QTqEpisodeData;
import com.server.cms.data.request.wevtoon.QTqEpisodePostData;
import com.server.cms.data.response.webtoon.SEpisode;
import com.server.cms.domain.webtoon.TqWebtoonEpisode;
import com.server.cms.domain.webtoon.WebtoonEpisode;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.repository.TqWebtoonEpisodeRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.server.cms.config.security.SecurityUtils.currentUserInd;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Getter
@Service
@RequiredArgsConstructor
public class WebtoonEpisodeService {

    private final TqWebtoonEpisodeRepository tqWebtoonEpisodeRepository;

    public ResponsePageDTO findByWebtoonEpisodes(QTqEpisodeData.Search param) {
        Long userInd = currentUserInd();
        if(userInd == null) {
            throw new UserNotFoundException();
        }

        requiredBookCode(param.getBookCode());

        return tqWebtoonEpisodeRepository.findByWebtoonEpisodes(userInd, param);
    }

    public ResponsePageDTO findByEpisodes(QTqEpisodeData.Search param) {
        Long userInd = currentUserInd();
        if(userInd == null) {
            throw new UserNotFoundException();
        }

        return tqWebtoonEpisodeRepository.findByEpisodes(userInd, param);
    }

    public SEpisode.Item cpEpisodeSave(QTqEpisodePostData.Save param){
        TqWebtoonEpisode episode = TqWebtoonEpisode.create("", param);
        TqWebtoonEpisode save = tqWebtoonEpisodeRepository.save(episode);
        return SEpisode.Item.create(save);
    }

    private void requiredBookCode(String bookCode) {
        if(isEmpty(bookCode)) {
            throw new ContentNotFoundException("도서코드를 정확하지 않습니다.");
        }
    }

}
