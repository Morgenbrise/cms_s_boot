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

    public static final QTqWebtoon tqWebtoon = new QTqWebtoon("tqWebtoon");

    public final EnumPath<com.server.cms.type.YnType> adultYn = createEnum("adultYn", com.server.cms.type.YnType.class);

    public final StringPath author = createString("author");

    public final DateTimePath<java.time.LocalDateTime> closeDt = createDateTime("closeDt", java.time.LocalDateTime.class);

    public final ListPath<TqWebtoonEpisode, QTqWebtoonEpisode> episode = this.<TqWebtoonEpisode, QTqWebtoonEpisode>createList("episode", TqWebtoonEpisode.class, QTqWebtoonEpisode.class, PathInits.DIRECT2);

    public final StringPath genre = createString("genre");

    public final NumberPath<Long> ind = createNumber("ind", Long.class);

    public final DateTimePath<java.time.LocalDateTime> openDt = createDateTime("openDt", java.time.LocalDateTime.class);

    public final StringPath remark = createString("remark");

    public final EnumPath<com.server.cms.type.YnType> showYn = createEnum("showYn", com.server.cms.type.YnType.class);

    public final EnumPath<com.server.cms.type.TqContentStatusType> status = createEnum("status", com.server.cms.type.TqContentStatusType.class);

    public final ListPath<com.server.cms.domain.thumbnail.CpThumbnail, com.server.cms.domain.thumbnail.QCpThumbnail> thumbnails = this.<com.server.cms.domain.thumbnail.CpThumbnail, com.server.cms.domain.thumbnail.QCpThumbnail>createList("thumbnails", com.server.cms.domain.thumbnail.CpThumbnail.class, com.server.cms.domain.thumbnail.QCpThumbnail.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QTqWebtoon(String variable) {
        super(TqWebtoon.class, forVariable(variable));
    }

    public QTqWebtoon(Path<? extends TqWebtoon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTqWebtoon(PathMetadata metadata) {
        super(TqWebtoon.class, metadata);
    }

}

