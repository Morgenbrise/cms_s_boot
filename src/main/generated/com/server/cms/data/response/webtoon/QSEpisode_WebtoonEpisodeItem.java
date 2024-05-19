package com.server.cms.data.response.webtoon;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.server.cms.data.response.webtoon.QSEpisode_WebtoonEpisodeItem is a Querydsl Projection type for WebtoonEpisodeItem
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSEpisode_WebtoonEpisodeItem extends ConstructorExpression<SEpisode.WebtoonEpisodeItem> {

    private static final long serialVersionUID = 1747775130L;

    public QSEpisode_WebtoonEpisodeItem(com.querydsl.core.types.Expression<String> episodeCode, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<Integer> episodeNum, com.querydsl.core.types.Expression<Integer> order, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<String> thumbnailName, com.querydsl.core.types.Expression<com.server.cms.type.TqContentStatusType> status) {
        super(SEpisode.WebtoonEpisodeItem.class, new Class<?>[]{String.class, String.class, int.class, int.class, int.class, String.class, com.server.cms.type.TqContentStatusType.class}, episodeCode, title, episodeNum, order, price, thumbnailName, status);
    }

}

