package com.server.cms.data.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.server.cms.data.response.QResNotification is a Querydsl Projection type for ResNotification
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResNotification extends ConstructorExpression<ResNotification> {

    private static final long serialVersionUID = -975285345L;

    public QResNotification(com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<java.time.LocalDateTime> regDt) {
        super(ResNotification.class, new Class<?>[]{String.class, String.class, java.time.LocalDateTime.class}, title, content, regDt);
    }

}

