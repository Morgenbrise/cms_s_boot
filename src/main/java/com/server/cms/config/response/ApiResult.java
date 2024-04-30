package com.server.cms.config.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ApiResult<T> {

    @JsonProperty("SUCCESS")
    @Schema(name = "SUCCESS", description = "성공 유무", requiredMode = Schema.RequiredMode.REQUIRED)
    private final boolean success;

    @JsonProperty("RESPONSE")
    @Schema(name = "RESPONSE", description = "데이터", requiredMode = Schema.RequiredMode.REQUIRED)
    private final T response;

    @JsonProperty("ERROR")
    @Schema(name = "ERROR", description = "ERROR 정보", requiredMode = Schema.RequiredMode.REQUIRED)
    private final ApiError error;

    public static <T> ApiResult<T> OK(T responce) {
        return new ApiResult<>(true, responce, null);
    }

    public static ApiResult ERROR(Throwable throwable, HttpStatus status) {
        return new ApiResult(false, null, new ApiError(throwable, status));
    }

    public static ApiResult ERROR(String messgae, HttpStatus status) {
        return new ApiResult(false, null, new ApiError(messgae, status));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("SUCCESS", success)
                .append("RESPONSE", response)
                .append("ERROR", error)
                .build();
    }
}
