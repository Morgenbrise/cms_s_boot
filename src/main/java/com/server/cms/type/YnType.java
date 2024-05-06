package com.server.cms.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.server.cms.framework.inter.CodeType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public enum YnType implements CodeType {

    Y("Y", "사용"),
    N("N","미사용");

    private String code;
    private String value;

    YnType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @JsonCreator
    public static YnType of(String type) {
        if(isEmpty(type)) {
            return YnType.N;
        }

        return Arrays.stream(YnType.values())
                .filter(typeEnum -> typeEnum.code.equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 데이터가 존재하지 않습니다."));
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("CODE", code)
                .append("VALUE", value)
                .build();
    }
}
