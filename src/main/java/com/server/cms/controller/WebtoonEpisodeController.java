package com.server.cms.controller;

import com.server.cms.config.response.ApiResult;
import com.server.cms.data.request.wevtoon.ReqTqEpisodePostData;
import com.server.cms.data.response.webtoon.ResEpisode;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.service.WebtoonEpisodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.server.cms.config.response.ApiResult.OK;

@Tag(name = "웹툰 회차", description = "웹툰 회차 API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class WebtoonEpisodeController {

    private final WebtoonEpisodeService episodeService;

    @Operation(summary = "웹툰 회차 등록", description = "CP사 웹툰 회차를 등록합니다.")
    @PostMapping(path = "/api/cp/reg/webtoon/episode", consumes = {MediaType.APPLICATION_JSON_VALUE, "multipart/form-data"})
    public ApiResult saveCpEpisode(@RequestPart(value = "PARAM") ReqTqEpisodePostData.Save param
                                , @RequestPart(value = "THUMBNAIL", required = false) MultipartFile thumbnail
                                , @RequestPart(value = "MANUSCRIPT", required = false) MultipartFile manuscript) {
        log.info("PARAM : {}", param.toString());
        log.info("THUMBNAIL : {}", thumbnail.getOriginalFilename());
        log.info("MANUSCRIPT : {}", manuscript.getOriginalFilename());

        ResEpisode.Item item = episodeService.cpEpisodeSave(param, thumbnail, manuscript);
        log.info("EPISODE_SAVE_INFO : {}", item.toString());
        return OK(item);
    }

    @Operation(summary = "웹툰 회차 정보", description = "CP사 웹툰 회차 상세 정보를 가져온다.")
    @GetMapping(path = "/api/cp/webtoon/episode")
    public ApiResult getCpEpisodeInfo(@RequestParam("CD_EPISODE") String episodeCode) {
        log.info("EPISODE_CODE : {}", episodeCode);
        if(StringUtils.isEmpty(episodeCode)) {
            throw new ContentNotFoundException("도서코드가 존재하지 않습니다.");
        }

        ResEpisode.Info info = episodeService.findByCpEpisodeInfo(episodeCode);
        log.info("EPISODE_INFO : {}", info.toString());
        return OK(info);
    }

}
