package com.server.cms.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QToken is a Querydsl query type for Token
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QToken extends EntityPathBase<Token> {

    private static final long serialVersionUID = -1220448622L;

    public static final QToken token = new QToken("token");

    public final NumberPath<Long> ind = createNumber("ind", Long.class);

    public final StringPath refreshToken = createString("refreshToken");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath userId = createString("userId");

    public final EnumPath<com.server.cms.type.YnType> useYn = createEnum("useYn", com.server.cms.type.YnType.class);

    public QToken(String variable) {
        super(Token.class, forVariable(variable));
    }

    public QToken(Path<? extends Token> path) {
        super(path.getType(), path.getMetadata());
    }

    public QToken(PathMetadata metadata) {
        super(Token.class, metadata);
    }

}

