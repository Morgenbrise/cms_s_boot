package com.server.cms.data.response;

import com.querydsl.core.types.ConstructorExpression;
import com.server.cms.domain.webtoon.Webtoon;

import javax.annotation.processing.Generated;

/**
 * com.server.cms.data.response.QSWebtoon_Item is a Querydsl Projection type for Item
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSWebtoon_Item extends ConstructorExpression<SWebtoon.Item> {

    private static final long serialVersionUID = -1712505542L;

    public QSWebtoon_Item(com.querydsl.core.types.Expression<? extends Webtoon> entity) {
        super(SWebtoon.Item.class, new Class<?>[]{Webtoon.class}, entity);
    }

}
