package com.server.cms.data.response.webtoon;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.server.cms.data.response.webtoon.QSWebtoon_Item is a Querydsl Projection type for Item
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSWebtoon_Item extends ConstructorExpression<SWebtoon.Item> {

    private static final long serialVersionUID = -855976390L;

    public QSWebtoon_Item(com.querydsl.core.types.Expression<? extends com.server.cms.domain.webtoon.Webtoon> entity) {
        super(SWebtoon.Item.class, new Class<?>[]{com.server.cms.domain.webtoon.Webtoon.class}, entity);
    }

}
