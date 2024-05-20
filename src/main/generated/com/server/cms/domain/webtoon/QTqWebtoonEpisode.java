package com.server.cms.domain.webtoon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTqWebtoonEpisode is a Querydsl query type for TqWebtoonEpisode
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTqWebtoonEpisode extends EntityPathBase<TqWebtoonEpisode> {

    private static final long serialVersionUID = 1755487441L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTqWebtoonEpisode tqWebtoonEpisode = new QTqWebtoonEpisode("tqWebtoonEpisode");

    public final com.server.cms.framework.common.QBaseTimeEntity _super = new com.server.cms.framework.common.QBaseTimeEntity(this);

    public final StringPath episodeCode = createString("episodeCode");

    public final NumberPath<Integer> episodeNum = createNumber("episodeNum", Integer.class);

    public final EnumPath<com.server.cms.type.EpisodeType> episodeType = createEnum("episodeType", com.server.cms.type.EpisodeType.class);

    public final NumberPath<Long> ind = createNumber("ind", Long.class);

    public final DateTimePath<java.time.LocalDateTime> openDate = createDateTime("openDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> order = createNumber("order", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regId = _super.regId;

    public final EnumPath<com.server.cms.type.TqContentStatusType> status = createEnum("status", com.server.cms.type.TqContentStatusType.class);

    public final StringPath thumbnailName = createString("thumbnailName");

    public final StringPath title = createString("title");

    public final QTqWebtoon tqWebtoon;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDt = _super.updateDt;

    //inherited
    public final StringPath updateId = _super.updateId;

    public final EnumPath<com.server.cms.type.YnType> useYn = createEnum("useYn", com.server.cms.type.YnType.class);

    public QTqWebtoonEpisode(String variable) {
        this(TqWebtoonEpisode.class, forVariable(variable), INITS);
    }

    public QTqWebtoonEpisode(Path<? extends TqWebtoonEpisode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTqWebtoonEpisode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTqWebtoonEpisode(PathMetadata metadata, PathInits inits) {
        this(TqWebtoonEpisode.class, metadata, inits);
    }

    public QTqWebtoonEpisode(Class<? extends TqWebtoonEpisode> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tqWebtoon = inits.isInitialized("tqWebtoon") ? new QTqWebtoon(forProperty("tqWebtoon")) : null;
    }

}

