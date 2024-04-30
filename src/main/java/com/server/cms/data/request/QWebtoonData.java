package com.server.cms.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.cms.framework.common.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class QWebtoonData {

    @Getter
    public static class Search extends PageDTO {

        @Schema(name = "TITLE", description = "웹툰 제목", nullable = true, example = "나혼자...", deprecated = false)
        @JsonProperty("TITLE")
        private String title;

    }

}
