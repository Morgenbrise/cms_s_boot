package com.server.cms.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.domain.webtoon.Webtoon;
import com.server.cms.framework.common.CodeMapperValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static com.server.cms.framework.date.LocalDateUtil.getConvertDateTimeToString;

public class SCpWebtoon {

    @AllArgsConstructor
    @Schema(description = "웹툰 정보")
    public static class Item {

        @JsonProperty("IND")
        private Long ind;

        @JsonProperty("TITLE")
        private String title;

        @JsonProperty("REMARK")
        private String remark;

        @JsonProperty("AUTHOR_MN")
        private String author;

        @JsonProperty("OPEN_DT")
        private String openDt;

        @JsonProperty("CLOSE_DT")
        private String closeDt;

        @JsonProperty("ADULT_YN")
        private CodeMapperValue adultYn;

        public static Item form(TqWebtoon entity) {
            return new Item(entity.getInd(), entity.getTitle(), entity.getRemark()
                            , entity.getAuthor(), getConvertDateTimeToString(entity.getOpenDt())
                            , getConvertDateTimeToString(entity.getCloseDt()), entity.getAdultYn().getVo());
        }

        public static Item form(Webtoon entity) {
            return new Item(entity.getInd(), entity.getTitle(), entity.getRemark()
                    , entity.getAuthor(), getConvertDateTimeToString(entity.getOpenDt())
                    , getConvertDateTimeToString(entity.getCloseDt()), entity.getAdultYn().getVo());
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                    .append("ind", ind)
                    .append("title", title)
                    .append("remark", remark)
                    .append("author", author)
                    .append("openDt", openDt)
                    .append("closeDt", closeDt)
                    .append("adultYn", adultYn)
                    .build();
        }
    }

}
