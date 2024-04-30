package com.server.cms.config.security;

import com.server.cms.config.jwt.JwtAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static Long currentUserInd() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtAuthentication user = (JwtAuthentication) authentication.getPrincipal();
        return user.getSeq();
    }

}
