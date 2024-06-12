package com.server.cms.controller;

import com.server.cms.config.response.ApiResult;
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

    @GetMapping(path = "/api/support/noti")
    public ApiResult notification() {

        return null;
    }

}
