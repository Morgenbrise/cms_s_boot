package com.server.cms.controller;

import com.server.cms.config.annotation.QueryParam;
import com.server.cms.config.response.ApiResult;
import com.server.cms.config.security.SecurityUtils;
import com.server.cms.data.request.wevtoon.ReqTqEpisodeData;
import com.server.cms.data.request.wevtoon.ReqTqWebtoonPostData;
import com.server.cms.data.request.wevtoon.ReqWebtoonData;
import com.server.cms.data.response.webtoon.ResCpWebtoon;
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
import org.springframework.web.multipart.MultipartFile;

import static com.server.cms.config.response.ApiResult.OK;

@Tag(name = "웹툰", description = "웹툰 API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class WebtoonController {

    private final WebtoonService webtoonService;
    private final WebtoonEpisodeService episodeService;

    @Operation(summary = "웹툰 등록", description = "CP사에서 웹툰을 등록합니다.")
    @PostMapping(path = "/api/sv/cp/webtoon")
    public ApiResult saveCpWebtoon(@Valid @RequestPart ReqTqWebtoonPostData.Save param
                                    , @RequestPart(value = "THUMBNAIL", required = false) MultipartFile thumbnail) {
        ResCpWebtoon.Item item = webtoonService.saveCpWebtoon(param, thumbnail);
        return OK(item);
    }

    @Operation(summary = "웹툰 정보", description = "CP에 등록한 웹툰 정보를 보여 줍니다.")
    @GetMapping(path = "/api/cp/webtoons")
    public ApiResult getCpWebtoons(@QueryParam ReqWebtoonData.Search param) {
        String companyCode = SecurityUtils.currentCompanyCode();
        if(log.isDebugEnabled()) {
            log.info("getCpWetoons[{}] ----> {}", companyCode, param.toString());
        }
        if(StringUtils.isEmpty(companyCode)) {
            throw new UserNotFoundException();
        }
        ResponsePageDTO webtoons = webtoonService.findByCpWebtoons(companyCode, param);

        return OK(webtoons);
    }

    @Operation(summary = "웹툰 정보", description = "CP에 등록한 웹툰 정보를 보여 줍니다.")
    @GetMapping(path = "/api/cp/webtoon")
    public ApiResult getCpWeboonInfo(@QueryParam ReqTqEpisodeData.Search param) {
        if(StringUtils.isBlank(param.getBookCode())) {
            throw new ContentNotFoundException("도서 코드를 확인할수 없습니다.");
        }
        ResponsePageDTO episodes = episodeService.findByWebtoonEpisodes(param);
        return OK(episodes);
    }

    @Operation(summary = "웹툰 정보 수정", description = "CP에 등록한 웹툰 정보를 수정합니다.")
    @PatchMapping(path = "/api/mv/cp/webtoon")
    public ApiResult modifyCpWebtoon(@Valid @RequestBody ReqTqWebtoonPostData.Modify param) {
        ResCpWebtoon.Item item = webtoonService.modifyCpWebtoon(param);
        return OK(item);
    }

    @Operation(summary = "웹툰 리스트 정보", description = "등록된 웹툰 리스트 정보를 보여줍니다.")
    @GetMapping(path = "/api/webtoons")
    public ApiResult getWebtoons(@QueryParam ReqWebtoonData.Search param) {
        String companyCode = SecurityUtils.currentCompanyCode();
        if(log.isDebugEnabled()) {
            log.info("getWetoons[{}] ----> {}", companyCode, param.toString());
        }
        if(StringUtils.isEmpty(companyCode)) {
            throw new UserNotFoundException();
        }

        ResponsePageDTO webtoons = webtoonService.findByWebtoons(companyCode, param);
        return OK(webtoons);
    }

    @PutMapping(path = "/api/modify/webtoon")
    public ApiResult modify(@Valid @RequestBody ReqWebtoonData.Modify param) {

        return null;
    }

}
