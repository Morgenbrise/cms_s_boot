package com.server.cms.controller;

import com.server.cms.config.response.ApiResult;
import com.server.cms.data.request.wevtoon.QTqEpisodePostData;
import com.server.cms.service.WebtoonEpisodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
                                , @RequestPart(value = "THUMBNAIL", required = false) MultipartFile file) {
        log.info("PARAM : {}", param.toString());
        log.info("PARAM_TITLE : {}", param.getTitle());
        log.info("FILE : {}", file.getOriginalFilename());

        return OK(null);
    }

}
