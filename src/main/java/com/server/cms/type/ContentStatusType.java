package com.server.cms.type;

import com.server.cms.framework.inter.CodeType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ContentStatusType implements CodeType {

    OPEN("3000", "서비스중"),
    NO_OPEN("3000", "미노출"),
    DELETE("3100", "삭제");

    private String code;
    private String value;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }
}
