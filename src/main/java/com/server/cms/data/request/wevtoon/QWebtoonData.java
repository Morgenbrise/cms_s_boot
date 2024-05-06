package com.server.cms.data.request.wevtoon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.cms.framework.common.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QWebtoonData {

    @Getter
    public static class Search extends PageDTO {

        @Schema(name = "TITLE", description = "웹툰 제목", nullable = true, example = "나혼자...", deprecated = false)
        @JsonProperty("TITLE")
        private String title;

    }

    @Getter
    public static class Modify {

        @Schema(name = "IND", description = "웹툰 인덱스", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonProperty("IND")
        @NotNull(message = "")
        private Long ind;

        @Schema(name = "TITLE", description = "웹툰 제목", example = "나혼자...", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonProperty("TITLE")
        private String title;

        @Schema(name = "DT_OPEN", description = "오픈일자", example = "0000-00-00 00:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonProperty("DT_OPEN")
        private String openDt;

        @Schema(name = "DD_CLOSE", description = "종료일자", example = "0000-00-00")
        @JsonProperty("DD_CLOSE")
        private String closeDd;

        @Schema(name = "REMARK", description = "내용", example = "웹툰 설명", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonProperty("REMARK")
        private String remark;

        @Schema(name = "AUTHOR", description = "작가", example = "김작가", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonProperty("AUTHOR")
        private String author;

        @Schema(name = "YN_ADULT", description = "성인 여부", example = "성인/비성인", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonProperty("YN_ADULT")
        private String adultYn;

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                    .append("IND", ind)
                    .append("TITLE", title)
                    .append("OPEN_DT", openDt)
                    .append("CLOSE_DD", closeDd)
                    .append("REMARK", remark)
                    .append("AUTHOR", author)
                    .append("ADULT_YN", adultYn)
                    .build();
        }
    }

}
