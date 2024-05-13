package com.server.cms.domain.webtoon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWebtoonEpisode is a Querydsl query type for WebtoonEpisode
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWebtoonEpisode extends EntityPathBase<WebtoonEpisode> {

    private static final long serialVersionUID = 558638772L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWebtoonEpisode webtoonEpisode = new QWebtoonEpisode("webtoonEpisode");

    public final DateTimePath<java.time.LocalDateTime> closeDt = createDateTime("closeDt", java.time.LocalDateTime.class);

    public final NumberPath<Long> ind = createNumber("ind", Long.class);

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> openDt = createDateTime("openDt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> order = createNumber("order", Integer.class);

    public final StringPath path = createString("path");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final EnumPath<com.server.cms.type.ContentStatusType> status = createEnum("status", com.server.cms.type.ContentStatusType.class);

    public final StringPath title = createString("title");

    public final StringPath type = createString("type");

    public final EnumPath<com.server.cms.type.YnType> useYn = createEnum("useYn", com.server.cms.type.YnType.class);

    public final QWebtoon webtoon;

    public QWebtoonEpisode(String variable) {
        this(WebtoonEpisode.class, forVariable(variable), INITS);
    }

    public QWebtoonEpisode(Path<? extends WebtoonEpisode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWebtoonEpisode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWebtoonEpisode(PathMetadata metadata, PathInits inits) {
        this(WebtoonEpisode.class, metadata, inits);
    }

    public QWebtoonEpisode(Class<? extends WebtoonEpisode> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.webtoon = inits.isInitialized("webtoon") ? new QWebtoon(forProperty("webtoon")) : null;
    }

}

