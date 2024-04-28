package com.server.cms.framework.inter;

import com.server.cms.framework.common.CodeMapperValue;

public interface CodeType {

    String getName();
    String getCode();
    String getValue();

    default CodeMapperValue getVo() {
        return new CodeMapperValue(getName(), getValue());
    }

    default boolean is(String code) {
        return getCode().equals(code);
    }

}
