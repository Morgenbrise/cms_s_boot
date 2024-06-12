package com.server.cms.data.response.webtoon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import com.server.cms.domain.webtoon.Webtoon;
import com.server.cms.framework.common.CodeMapperValue;
import io.swagger.v3.oas.annotations.media.Schema;

import static com.server.cms.framework.date.LocalDateUtil.getConvertDateTimeToString;

public class ResWebtoon {

    @Schema(description = "웹툰 리스트 정보")
    public static class Item {

        @Schema(name = "IND", defaultValue = "", description = "웹툰 인덱스")
        @JsonProperty("IND")
        private Long ind;

        @Schema(name = "TITLE", description = "웹툰 제목")
        @JsonProperty("TITLE")
        private String title;

        @Schema(name = "DT_OPEN", description = "오픈 날짜")
        @JsonProperty("DT_OPEN")
        private String openDt;

        @Schema(name = "ADULT", description = "성인 여부")
        @JsonProperty("ADULT")
        private CodeMapperValue adult;

        @Schema(name = "DT_CLOSE", description = "종료 날짜")
        @JsonProperty("DT_CLOSE")
        private String closeDt;

        @Schema(name = "NM_AUTHOR", description = "작가명")
        @JsonProperty("NM_AUTHOR")
        private String author;

        @QueryProjection
        public Item(Webtoon entity) {
            this.ind = entity.getInd();
            this.title = entity.getTitle();
            this.openDt = getConvertDateTimeToString(entity.getOpenDt());
            this.adult = CodeMapperValue.form(entity.getAdultYn());
            this.closeDt = getConvertDateTimeToString(entity.getCloseDt());
            this.author = entity.getAuthor();
        }
    }

}
