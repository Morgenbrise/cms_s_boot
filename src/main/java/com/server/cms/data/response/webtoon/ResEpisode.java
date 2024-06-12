package com.server.cms.data.response.webtoon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import com.server.cms.domain.manuscript.InspectManuscript;
import com.server.cms.domain.manuscript.Manuscript;
import com.server.cms.domain.webtoon.TqWebtoonEpisode;
import com.server.cms.framework.common.CodeMapperValue;
import com.server.cms.framework.date.LocalDateUtil;
import com.server.cms.type.ContentStatusType;
import com.server.cms.type.TqContentStatusType;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResEpisode {

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

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                    .append("EPISODE_CODE", episodeCode)
                    .append("WEBTOON_TITLE", webtoonTitle)
                    .append("TITLE", title)
                    .append("EPISODE_NO", episodeNum)
                    .append("ORDER", order)
                    .append("PRICE", price)
                    .append("THUMBNAIL", thumbnailName)
                    .append("STATUS", status)
                    .toString();
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
        private String thumbnailPath;

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
            this.thumbnailPath = thumbnailName;
            this.status = status.getVo();
        }

    }

    @AllArgsConstructor
    public static class Info {

        private String episodeCode;

        private String title;

        private int episodeNo;

        private int order;

        private int price;

        private CodeMapperValue type;

        private String thumbnail;

        private String openDt;

        private CodeMapperValue useYn;

        private CodeMapperValue status;

        private List<String> inspect = new ArrayList<>();

        private List<String> manuscript = new ArrayList<>();

        public static Info form(TqWebtoonEpisode entity) {
            List<InspectManuscript> inspectManuscript = entity.getInspectManuscript();
            List<Manuscript> manuscript = entity.getManuscript();

            List<String> strInspectManuscript = inspectManuscript.stream()
                                                    .map(i -> i.getPath() + "/" + i.getImageNm())
                                                    .collect(Collectors.toList());

            List<String> strManuscript = manuscript.stream()
                                                    .map(i -> i.getPath() + "/" + i.getImageNm())
                                                    .collect(Collectors.toList());

            return new Info(entity.getEpisodeCode(), entity.getTitle(), entity.getEpisodeNum()
                        , entity.getOrder(), entity.getPrice(), entity.getEpisodeType().getVo(), (entity.getPath() + entity.getThumbnailName())
                        , LocalDateUtil.getConvertDateTimeToString(entity.getOpenDate())
                        , entity.getUseYn().getVo(), entity.getStatus().getVo(), strInspectManuscript, strManuscript);

        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("EPISODE_CODE", episodeCode)
                    .append("TITLE", title)
                    .append("EPISODE_NO", episodeNo)
                    .append("ORDER", order)
                    .append("PRICE", price)
                    .append("TYPE", type)
                    .append("THUMBNAIL", thumbnail)
                    .append("OPEN_DATE", openDt)
                    .append("USE_YN", useYn)
                    .append("STATUS", status)
                    .append("INSPECT", inspect.size())
                    .append("MANUSCRIPT", manuscript.size())
                    .toString();
        }
    }

}
