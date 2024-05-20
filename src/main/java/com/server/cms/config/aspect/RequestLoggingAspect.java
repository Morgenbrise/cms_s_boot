package com.server.cms.config.aspect;

import com.google.common.base.Joiner;
import com.server.cms.config.jwt.JwtAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@Aspect
public class RequestLoggingAspect {

    @Pointcut("within(com.server.cms.controller.*)")
    public void onRequest() {}

    @Around("com.server.cms.config.aspect.RequestLoggingAspect.onRequest()")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        RereadableRequestWrapper rereadableRequestWrapper = new RereadableRequestWrapper(request);
        Map<String, String[]> paramMap = rereadableRequestWrapper.getParameterMap();
        String params = "";
        String userId = getUserId();
        if (paramMap.isEmpty() == false) {
            params = " [" + paramMapToString(paramMap) + "]";
        }

        long start = System.currentTimeMillis();
        Object proceed = null;
        try {
            proceed = pjp.proceed(pjp.getArgs());
            return proceed;
        } finally {
            long end = System.currentTimeMillis();
            log.info("@ASPECT REQUEST    [{}]    : {} {}{} < {} ({}ms)", userId, request.getMethod(), request.getRequestURI(), params, request.getRemoteHost(), end - start);
            if ("POST".equalsIgnoreCase(request.getMethod()) || "PATCH".equalsIgnoreCase(request.getMethod())) {
//                log.info("@ASPECT POST BODY  [{}]    : {} ", userId, IOUtils.toString(request.getReader()));
            }
            log.info("@ASPECT RESPONSE   [{}]    : {} {}{} < {} ({}ms)", userId, request.getMethod(), request.getRequestURI(), proceed, request.getRemoteHost(), end - start);
        }
    }

    private String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = "";
        if(authentication != null && authentication.isAuthenticated() && (authentication.getPrincipal() instanceof JwtAuthentication)) {
            JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication.getPrincipal();
            userId = jwtAuthentication.getUserId();
        }
        return userId;
    }

    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(entry -> String.format("%s -> (%s)",
                        entry.getKey(), Joiner.on(",").join(entry.getValue())))
                .collect(Collectors.joining(", "));
    }
}