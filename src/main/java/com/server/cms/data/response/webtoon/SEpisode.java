package com.server.cms.data.response.webtoon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import com.server.cms.domain.webtoon.TqWebtoonEpisode;
import com.server.cms.framework.common.CodeMapperValue;
import com.server.cms.type.ContentStatusType;
import com.server.cms.type.TqContentStatusType;
import lombok.AllArgsConstructor;

public class SEpisode {

    @AllArgsConstructor
    public static class Item {

        @JsonProperty("CD_EPISODE")
        private String episodeCode;

        @JsonProperty("WEBTOON_TITLE")
        private String webtoonTitle;

        @JsonProperty("TITLE")
        private String title;

        @JsonProperty("NO_EPISODE")
        private Integer episodeNum;

        @JsonProperty("NO_ORDER")
        private Integer order;

        @JsonProperty("PRICE")
        private Integer price;

        @JsonProperty("THUMBNAIL")
        private String thumbnailName;

        @JsonProperty("STATUS")
        private CodeMapperValue status;

        @QueryProjection
        public Item(String episodeCode, String webtoonTitle, String title, Integer episodeNum, Integer order
                , Integer price, String thumbnailName, TqContentStatusType status) {
            this.episodeCode = episodeCode;
            this.webtoonTitle = webtoonTitle;
            this.title = title;
            this.episodeNum = episodeNum;
            this.order = order;
            this.price = price;
            this.thumbnailName = thumbnailName;
            this.status = status.getVo();
        }

        public static Item create(TqWebtoonEpisode entity) {
            return new Item(entity.getEpisodeCode(), null, entity.getTitle(), entity.getEpisodeNum(), entity.getOrder()
                            , entity.getPrice(), entity.getThumbnailName(), entity.getStatus());
        }
    }

    public static class WebtoonEpisodeItem {

        @JsonProperty("CD_EPISODE")
        private String episodeCode;

        @JsonProperty("TITLE")
        private String title;

        @JsonProperty("NO_EPISODE")
        private Integer episodeNum;

        @JsonProperty("NO_ORDER")
        private Integer order;

        @JsonProperty("PRICE")
        private Integer price;

        @JsonProperty("THUMBNAIL")
        private String thumbnailName;

        @JsonProperty("STATUS")
        private CodeMapperValue status;

        @QueryProjection
        public WebtoonEpisodeItem(String episodeCode, String title, Integer episodeNum, Integer order
                , Integer price, String thumbnailName, TqContentStatusType status) {
            this.episodeCode = episodeCode;
            this.title = title;
            this.episodeNum = episodeNum;
            this.order = order;
            this.price = price;
            this.thumbnailName = thumbnailName;
            this.status = status.getVo();
        }

    }

}
