package com.server.cms.controller;

import com.server.cms.config.annotation.QueryParam;
import com.server.cms.config.response.ApiResult;
import com.server.cms.data.request.ReqNotification;
import com.server.cms.service.NotiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Tag(name = "", description = "")
@Slf4j
@RestController
@RequiredArgsConstructor
public class SupportController {

    private final NotiService notiService;

    @GetMapping(path = "/api/support/noti")
    public ApiResult notification(@QueryParam ReqNotification param) {
        return ApiResult.OK(notiService.findByNotis(param));
    }

}
