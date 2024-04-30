package com.server.cms.config.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiError {

    @JsonProperty("MESSAGE")
    @Schema(name = "MESSAGE", description = "오류 메시지", requiredMode = Schema.RequiredMode.AUTO)
    private final String message;

    @JsonProperty("STATUS")
    @Schema(name = "STATUS", description = "오류 상태값", requiredMode = Schema.RequiredMode.AUTO)
    private final int status;

    public ApiError(Throwable throwable, HttpStatus status) {
        this(throwable.getMessage(), status);
    }

    public ApiError(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }

}
