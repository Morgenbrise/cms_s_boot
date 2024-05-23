package com.server.cms.controller;

import com.server.cms.config.response.ApiResult;
import com.server.cms.data.request.wevtoon.QTqEpisodePostData;
import com.server.cms.data.response.webtoon.SEpisode;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.service.WebtoonEpisodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.server.cms.config.response.ApiResult.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WebtoonEpisodeController {

    private final WebtoonEpisodeService episodeService;

    @PostMapping(path = "/api/cp/reg/webtoon/episode", consumes = {MediaType.APPLICATION_JSON_VALUE, "multipart/form-data"})
    public ApiResult saveCpEpisode(@RequestPart(value = "PARAM") QTqEpisodePostData.Save param
                                , @RequestPart(value = "THUMBNAIL", required = false) MultipartFile thumbnail
                                , @RequestPart(value = "MANUSCRIPT", required = false) MultipartFile manuscript) {
        log.info("PARAM : {}", param.toString());
        log.info("THUMBNAIL : {}", thumbnail.getOriginalFilename());
        log.info("MANUSCRIPT : {}", manuscript.getOriginalFilename());

        SEpisode.Item item = episodeService.cpEpisodeSave(param, thumbnail, manuscript);
        log.info("EPISODE_SAVE_INFO : {}", item.toString());
        return OK(item);
    }

    @GetMapping(path = "/api/cp/webtoon/episode")
    public ApiResult getCpEpisodeInfo(@RequestParam("CD_EPISODE") String episodeCode) {
        log.info("EPISODE_CODE : {}", episodeCode);
        if(StringUtils.isEmpty(episodeCode)) {
            throw new ContentNotFoundException("도서코드가 존재하지 않습니다.");
        }

        SEpisode.Info info = episodeService.findByCpEpisodeInfo(episodeCode);
        log.info("EPISODE_INFO : {}", info.toString());
        return OK(info);
    }

}
