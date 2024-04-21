package com.server.cms.config.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ApiResult<T> {

    private final boolean success;

    private final ApiError error;

    public static ApiResult ERROR(String messgae, HttpStatus status) {
        return new ApiResult(false,  new ApiError(messgae, status));
    }

    public static ApiResult ERROR(Throwable throwable, HttpStatus status) {
        return new ApiResult(false, new ApiError(throwable, status));
    }
}
