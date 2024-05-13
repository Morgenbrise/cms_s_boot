package com.server.cms.type;

import com.server.cms.framework.common.CodeMapperValue;
import com.server.cms.framework.inter.CodeType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@AllArgsConstructor
public enum TqContentStatusType implements CodeType {

    REQUEST_APPLY("1000", "신청"),
    REQUEST_SERVICE_APPLY("1010", "신청중(서비스중)"),
    REQUEST_MODIFY("1100", "수정"),
    REQUEST_SERVICE_MODIFY("1110", "수정(서비스중)"),
    REQUEST_DELETE("1200", "삭제 요청"),
    UNDER_REVIEW("2000", "검토중"),
    UNDER_SERVICE_REVIEW("2010", "검토중(서비스중)"),
    REVIEW_COMPLETED("2100", "검토 완료"),
    REVIEW_SERVICE_COMPLETED("2110", "검토 완료(서비스중)"),
    OPEN("3000", "서비스중"),
    COMPANION("3100", "반려"),
    DELETE("3100", "삭제");

    private final String code;
    private final String value;

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
    public CodeMapperValue getVo() {
        return CodeType.super.getVo();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("CODE", code)
                .append("VALUE", value)
                .build();
    }
}
