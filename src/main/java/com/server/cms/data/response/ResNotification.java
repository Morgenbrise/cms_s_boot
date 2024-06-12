package com.server.cms.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import com.server.cms.framework.date.LocalDateUtil;

import java.time.LocalDateTime;

public class ResNotification {

    @JsonProperty("TITLE")
    private String title;

    @JsonProperty("CONTENT")
    private String content;

    @JsonProperty("DT_REG")
    private String regDt;

    @QueryProjection
    public ResNotification(String title, String content, LocalDateTime regDt) {
        this.title = title;
        this.content = content;
        this.regDt = LocalDateUtil.getConvertDateTimeToString(regDt, "YYYY-MM-dd");
    }
}
