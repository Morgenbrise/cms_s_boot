package com.server.cms.config.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.cms.config.annotation.QueryParam;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
@RequiredArgsConstructor
public class QueryStringArgumentResolver implements HandlerMethodArgumentResolver {

    private final ObjectMapper mapper;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(QueryParam.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        final HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_AR RAY, true);
        return mapper.readValue(qs2json(request.getQueryString()), methodParameter.getParameterType());
    }

    private String qs2json(String a) {
        String res = "{\"";
        if(isBlank(a)) {
            return null;
        }
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '=') {
                res += "\"" + ":" + "\"";
            } else if (a.charAt(i) == '&') {
                res += "\"" + "," + "\"";
            } else {
                res += a.charAt(i);
            }
        }
        res += "\"" + "}";
        try {
            String decode = URLDecoder.decode(res, "UTF-8");
            return decode;
        } catch (UnsupportedEncodingException e) {
            return res;
        }
    }

}
