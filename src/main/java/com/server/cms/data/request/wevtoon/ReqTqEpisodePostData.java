package com.server.cms.data.request.wevtoon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.cms.type.EpisodeType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ReqTqEpisodePostData {

    @Getter
    public static class Save {

        @NotEmpty(message = "웹툰 정보가 존재하지 않습니다.")
        @JsonProperty("CD_BOOK")
        private String bookCode;

        @JsonProperty("TITLE")
        private String title;

        @JsonProperty("NO_EPISODE")
        private int episodeNum;

        @JsonProperty("NO_ORDER")
        private int order;

        @NotNull(message = "구매 금액은 필수 입력 항목입니다.")
        @JsonProperty("PRICE")
        private Integer price;

        @JsonProperty("TP_EPISODE")
        private EpisodeType episodeType;

        @NotEmpty(message = "오픈일자는 필수 입력 항목입니다.")
        @JsonProperty("DT_OPEN")
        private String openDate;

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                    .append("TITLE", title)
                    .append("NO_EPISODE", episodeNum)
                    .append("ORDER", order)
                    .append("PRICE", price)
                    .append("TP_EPISODE", episodeType)
                    .append("DT_OPEN", openDate)
                    .build();
        }
    }

}
