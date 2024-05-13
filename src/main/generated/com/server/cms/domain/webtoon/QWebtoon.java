package com.server.cms.domain.webtoon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWebtoon is a Querydsl query type for Webtoon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWebtoon extends EntityPathBase<Webtoon> {

    private static final long serialVersionUID = -1657896633L;

    public static final QWebtoon webtoon = new QWebtoon("webtoon");

    public final EnumPath<com.server.cms.type.YnType> adultYn = createEnum("adultYn", com.server.cms.type.YnType.class);

    public final StringPath author = createString("author");

    public final DateTimePath<java.time.LocalDateTime> closeDt = createDateTime("closeDt", java.time.LocalDateTime.class);

    public final ListPath<WebtoonEpisode, QWebtoonEpisode> episode = this.<WebtoonEpisode, QWebtoonEpisode>createList("episode", WebtoonEpisode.class, QWebtoonEpisode.class, PathInits.DIRECT2);

    public final StringPath genre = createString("genre");

    public final NumberPath<Long> ind = createNumber("ind", Long.class);

    public final DateTimePath<java.time.LocalDateTime> openDt = createDateTime("openDt", java.time.LocalDateTime.class);

    public final StringPath remark = createString("remark");

    public final EnumPath<com.server.cms.type.ContentStatusType> status = createEnum("status", com.server.cms.type.ContentStatusType.class);

    public final StringPath title = createString("title");

    public QWebtoon(String variable) {
        super(Webtoon.class, forVariable(variable));
    }

    public QWebtoon(Path<? extends Webtoon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWebtoon(PathMetadata metadata) {
        super(Webtoon.class, metadata);
    }

}

