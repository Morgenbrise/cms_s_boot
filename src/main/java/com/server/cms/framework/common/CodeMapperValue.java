package com.server.cms.framework.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.cms.framework.inter.CodeType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CodeMapperValue {

    @JsonProperty("CODE")
    private String code;

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
