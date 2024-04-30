package com.server.cms.framework.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {

    @Schema(name =  "PAGE", type = "int", description="페이지 번호", defaultValue = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(value = "PAGE")
    private int page;

    @Schema(name = "OFFSET", type = "int", description = "페이지 컨텐츠 수", defaultValue = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(value = "OFFSET")
    private int offset;

}
