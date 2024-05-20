package com.server.cms.config.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

    private final JWT jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // favicon.ico 요청에 대한 JWT 토큰 검증을 건너뛰기
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        if ("/favicon.ico".equals(request.getRequestURI())) {
            return true;
        }

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        // 쿠키에서 JWT 토큰 가져오기
//        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (jakarta.servlet.http.Cookie cookie : cookies) {
//                if (cookie.getName().equals("jwt")) {
//                    token = cookie.getValue();
//                    break;
//                }
//            }
//        }

        if (token != null) {

            String accessToken = "";
            try {
                accessToken = token.replaceAll("Bearer ", "");
            } catch (NullPointerException e) {
//            throw new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST);
                return false;
            }

            JWT.Claims verify = jwt.verify(accessToken);
            String userId = verify.getUserId();
            if (userId == null) {
                log.debug("token isn't userId");
//                throw new ProfileApplicationException(ErrorCode.AUTH_TOKEN_NOT_MATCH);
                return false;
            }
            return true;
        } else {
//            throw new ProfileApplicationException(ErrorCode.AUTH_TOKEN_IS_NULL);
            return false;
        }
    }
}
