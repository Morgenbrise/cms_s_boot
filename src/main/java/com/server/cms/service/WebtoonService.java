package com.server.cms.service;

import com.server.cms.data.request.QWebtoonData;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;

    public ResponsePageDTO findByWebtoons(Long userInd, QWebtoonData.Search param) {
        if(userInd == null) {
            throw new UserNotFoundException();
        }

        return webtoonRepository.findByWebtoons(userInd, param);
    }

}
