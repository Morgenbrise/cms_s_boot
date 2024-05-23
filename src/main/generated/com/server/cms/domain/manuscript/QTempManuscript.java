package com.server.cms.domain.manuscript;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTempManuscript is a Querydsl query type for TempManuscript
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTempManuscript extends EntityPathBase<TempManuscript> {

    private static final long serialVersionUID = 1849290811L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTempManuscript tempManuscript = new QTempManuscript("tempManuscript");

    public final QManuscriptItem _super = new QManuscriptItem(this);

    public final com.server.cms.domain.webtoon.QTqWebtoonEpisode episode;

    //inherited
    public final StringPath ext = _super.ext;

    //inherited
    public final StringPath imageNm = _super.imageNm;

    //inherited
    public final NumberPath<Long> ind = _super.ind;

    //inherited
    public final StringPath path = _super.path;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regId = _super.regId;

    //inherited
    public final NumberPath<Long> size = _super.size;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDt = _super.updateDt;

    //inherited
    public final StringPath updateId = _super.updateId;

    //inherited
    public final EnumPath<com.server.cms.type.YnType> useYn = _super.useYn;

    public QTempManuscript(String variable) {
        this(TempManuscript.class, forVariable(variable), INITS);
    }

    public QTempManuscript(Path<? extends TempManuscript> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTempManuscript(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTempManuscript(PathMetadata metadata, PathInits inits) {
        this(TempManuscript.class, metadata, inits);
    }

    public QTempManuscript(Class<? extends TempManuscript> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.episode = inits.isInitialized("episode") ? new com.server.cms.domain.webtoon.QTqWebtoonEpisode(forProperty("episode"), inits.get("episode")) : null;
    }

}

