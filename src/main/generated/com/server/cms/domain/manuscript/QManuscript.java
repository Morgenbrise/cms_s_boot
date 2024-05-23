package com.server.cms.domain.manuscript;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QManuscript is a Querydsl query type for Manuscript
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QManuscript extends EntityPathBase<Manuscript> {

    private static final long serialVersionUID = -1527782969L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QManuscript manuscript = new QManuscript("manuscript");

    public final QManuscriptItem _super = new QManuscriptItem(this);

    public final com.server.cms.domain.webtoon.QWebtoonEpisode episode;

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

    public QManuscript(String variable) {
        this(Manuscript.class, forVariable(variable), INITS);
    }

    public QManuscript(Path<? extends Manuscript> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QManuscript(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QManuscript(PathMetadata metadata, PathInits inits) {
        this(Manuscript.class, metadata, inits);
    }

    public QManuscript(Class<? extends Manuscript> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.episode = inits.isInitialized("episode") ? new com.server.cms.domain.webtoon.QWebtoonEpisode(forProperty("episode"), inits.get("episode")) : null;
    }

}

