package com.server.cms.controller;

import com.server.cms.config.annotation.QueryParam;
import com.server.cms.config.response.ApiResult;
import com.server.cms.config.security.SecurityUtils;
import com.server.cms.data.request.wevtoon.QTqEpisodeData;
import com.server.cms.data.request.wevtoon.QTqWebtoonPostData;
import com.server.cms.data.request.wevtoon.QWebtoonData;
import com.server.cms.data.response.webtoon.SCpWebtoon;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.service.WebtoonEpisodeService;
import com.server.cms.service.WebtoonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.NotContextException;

import static com.server.cms.config.response.ApiResult.OK;

@Tag(name = "웹툰", description = "웹툰 API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class WebtoonController {

    private final WebtoonService webtoonService;
    private final WebtoonEpisodeService episodeService;

    @PostMapping(path = "/api/sv/cp/webtoon")
    public ApiResult saveHisWebtoon(@Valid @RequestBody QTqWebtoonPostData.Save param) {
        SCpWebtoon.Item item = webtoonService.saveCpWebtoon(param);
        return OK(item);
    }

    @GetMapping(path = "/api/cp/webtoons")
    public ApiResult getCpWebtoons(@QueryParam QWebtoonData.Search param) {
        Long ind = SecurityUtils.currentUserInd();
        if(log.isDebugEnabled()) {
            log.info("getCpWetoons[{}] ----> {}", ind, param.toString());
        }
        if(ind == null) {
            throw new UserNotFoundException();
        }
        ResponsePageDTO webtoons = webtoonService.findByCpWebtoons(ind, param);

        return OK(webtoons);
    }

    @GetMapping(path = "/api/cp/webtoon")
    public ApiResult getCpWeboonInfo(@QueryParam QTqEpisodeData.Search param) {
        if(StringUtils.isBlank(param.getBookCode())) {
            throw new ContentNotFoundException("도서 코드를 확인할수 없습니다.");
        }
        ResponsePageDTO episodes = episodeService.findByWebtoonEpisodes(param);
        return OK(episodes);
    }

    @PatchMapping(path = "/api/mv/cp/webtoon")
    public ApiResult modifyCpWebtoon(@Valid @RequestBody QTqWebtoonPostData.Modify param) {
        SCpWebtoon.Item item = webtoonService.modifyCpWebtoon(param);
        return OK(item);
    }

    @Operation(summary = "웹툰 리스트 정보", description = "등록된 웹툰 리스트 정보를 보여줍니다.")
    @GetMapping(path = "/api/webtoons")
    public ApiResult getWebtoons(@QueryParam QWebtoonData.Search param) {
        Long ind = SecurityUtils.currentUserInd();
        if(log.isDebugEnabled()) {
            log.info("getWetoons[{}] ----> {}", ind, param.toString());
        }
        if(ind == null) {
            throw new UserNotFoundException();
        }

        ResponsePageDTO webtoons = webtoonService.findByWebtoons(ind, param);
        return OK(webtoons);
    }

    @PutMapping(path = "/api/modify/webtoon")
    public ApiResult modify(@Valid @RequestBody QWebtoonData.Modify param) {

        return null;
    }

}
