package com.server.cms.config.security;

import com.server.cms.config.jwt.JwtAuthentication;
import com.server.cms.framework.error.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static Long currentUserInd() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(principal instanceof JwtAuthentication) {
            JwtAuthentication user = (JwtAuthentication) principal;
            return user.getSeq();
        }

        throw new UserNotFoundException();
    }

}
