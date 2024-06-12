package com.server.cms.data.response.webtoon;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.server.cms.data.response.webtoon.QResEpisode_Item is a Querydsl Projection type for Item
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResEpisode_Item extends ConstructorExpression<ResEpisode.Item> {

    private static final long serialVersionUID = -1509485830L;

    public QResEpisode_Item(com.querydsl.core.types.Expression<String> episodeCode, com.querydsl.core.types.Expression<String> webtoonTitle, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<Integer> episodeNum, com.querydsl.core.types.Expression<Integer> order, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<String> thumbnailName, com.querydsl.core.types.Expression<com.server.cms.type.TqContentStatusType> status) {
        super(ResEpisode.Item.class, new Class<?>[]{String.class, String.class, String.class, int.class, int.class, int.class, String.class, com.server.cms.type.TqContentStatusType.class}, episodeCode, webtoonTitle, title, episodeNum, order, price, thumbnailName, status);
    }

}

