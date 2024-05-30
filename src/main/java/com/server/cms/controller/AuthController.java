package com.server.cms.controller;

import com.server.cms.config.jwt.AuthenticationRequest;
import com.server.cms.config.jwt.AuthenticationResult;
import com.server.cms.config.jwt.JwtAuthenticationToken;
import com.server.cms.config.response.ApiResult;
import com.server.cms.config.security.SecurityUtils;
import com.server.cms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping(path = "/auth/signup")
    public ApiResult login(@Valid @RequestBody AuthenticationRequest param) {
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(param.getId(), param.getPassword());
        Authentication authentication = authenticationManager.authenticate(jwtAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticationResult authenticationResult = (AuthenticationResult) authentication.getDetails();
        return ApiResult.OK(authenticationResult);
    }

    @PostMapping(path = "/auth/logout")
    public ApiResult logout() {
        String cpId = SecurityUtils.currentCpId();
        userService.deleteRefreshToken(cpId);
        return ApiResult.OK(null);
    }

}
