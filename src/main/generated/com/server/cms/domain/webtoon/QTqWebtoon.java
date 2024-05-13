package com.server.cms.domain.webtoon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTqWebtoon is a Querydsl query type for TqWebtoon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTqWebtoon extends EntityPathBase<TqWebtoon> {

    private static final long serialVersionUID = -853409334L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTqWebtoon tqWebtoon = new QTqWebtoon("tqWebtoon");

    public final EnumPath<com.server.cms.type.YnType> adultYn = createEnum("adultYn", com.server.cms.type.YnType.class);

    public final StringPath author = createString("author");

    public final DateTimePath<java.time.LocalDateTime> closeDt = createDateTime("closeDt", java.time.LocalDateTime.class);

    public final ListPath<TqWebtoonEpisode, QTqWebtoonEpisode> epi = this.<TqWebtoonEpisode, QTqWebtoonEpisode>createList("epi", TqWebtoonEpisode.class, QTqWebtoonEpisode.class, PathInits.DIRECT2);

    public final StringPath genre = createString("genre");

    public final NumberPath<Long> ind = createNumber("ind", Long.class);

    public final DateTimePath<java.time.LocalDateTime> openDt = createDateTime("openDt", java.time.LocalDateTime.class);

    public final StringPath remark = createString("remark");

    public final EnumPath<com.server.cms.type.YnType> showYn = createEnum("showYn", com.server.cms.type.YnType.class);

    public final EnumPath<com.server.cms.type.TqContentStatusType> status = createEnum("status", com.server.cms.type.TqContentStatusType.class);

    public final StringPath title = createString("title");

    public final QWebtoon webtoon;

    public QTqWebtoon(String variable) {
        this(TqWebtoon.class, forVariable(variable), INITS);
    }

    public QTqWebtoon(Path<? extends TqWebtoon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTqWebtoon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTqWebtoon(PathMetadata metadata, PathInits inits) {
        this(TqWebtoon.class, metadata, inits);
    }

    public QTqWebtoon(Class<? extends TqWebtoon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.webtoon = inits.isInitialized("webtoon") ? new QWebtoon(forProperty("webtoon")) : null;
    }

}

