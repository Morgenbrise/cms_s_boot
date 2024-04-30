package com.server.cms.framework.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.cms.framework.inter.CodeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "코드 공통 JSON")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CodeMapperValue {

    @Schema(name = "CODE", description = "코드값", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("CODE")
    private String code;

    @Schema(name = "VALUE", description = "값", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("VALUE")
    private String msg;

    public CodeMapperValue(CodeType e) {
        this.code = e.getCode();
        this.msg = e.getValue();
    }

    public static CodeMapperValue form(String code, String msg) {
        return new CodeMapperValue(code, msg);
    }

    public static CodeMapperValue form(CodeType type) {
        return new CodeMapperValue(type.getCode(), type.getValue());
    }
}
