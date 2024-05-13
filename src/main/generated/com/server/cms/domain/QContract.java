package com.server.cms.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContract is a Querydsl query type for Contract
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContract extends EntityPathBase<Contract> {

    private static final long serialVersionUID = -949786151L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContract contract = new QContract("contract");

    public final StringPath bookCode = createString("bookCode");

    public final QCpUser cpUser;

    public final NumberPath<Long> ind = createNumber("ind", Long.class);

    public final com.server.cms.domain.webtoon.QTqWebtoon tqWebtoon;

    public final StringPath type = createString("type");

    public final com.server.cms.domain.webtoon.QWebtoon webtoon;

    public QContract(String variable) {
        this(Contract.class, forVariable(variable), INITS);
    }

    public QContract(Path<? extends Contract> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContract(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContract(PathMetadata metadata, PathInits inits) {
        this(Contract.class, metadata, inits);
    }

    public QContract(Class<? extends Contract> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cpUser = inits.isInitialized("cpUser") ? new QCpUser(forProperty("cpUser")) : null;
        this.tqWebtoon = inits.isInitialized("tqWebtoon") ? new com.server.cms.domain.webtoon.QTqWebtoon(forProperty("tqWebtoon"), inits.get("tqWebtoon")) : null;
        this.webtoon = inits.isInitialized("webtoon") ? new com.server.cms.domain.webtoon.QWebtoon(forProperty("webtoon")) : null;
    }

}

