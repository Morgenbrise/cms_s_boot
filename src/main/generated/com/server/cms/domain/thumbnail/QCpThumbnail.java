package com.server.cms.domain.thumbnail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCpThumbnail is a Querydsl query type for CpThumbnail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCpThumbnail extends EntityPathBase<CpThumbnail> {

    private static final long serialVersionUID = -214970474L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCpThumbnail cpThumbnail = new QCpThumbnail("cpThumbnail");

    public final QThumbnailItem _super = new QThumbnailItem(this);

    //inherited
    public final StringPath ext = _super.ext;

    //inherited
    public final StringPath imageName = _super.imageName;

    //inherited
    public final NumberPath<Long> ind = _super.ind;

    //inherited
    public final StringPath path = _super.path;

    //inherited
    public final NumberPath<Long> size = _super.size;

    public final com.server.cms.domain.webtoon.QTqWebtoon tqWebtoon;

    //inherited
    public final EnumPath<com.server.cms.type.YnType> useYn = _super.useYn;

    public QCpThumbnail(String variable) {
        this(CpThumbnail.class, forVariable(variable), INITS);
    }

    public QCpThumbnail(Path<? extends CpThumbnail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCpThumbnail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCpThumbnail(PathMetadata metadata, PathInits inits) {
        this(CpThumbnail.class, metadata, inits);
    }

    public QCpThumbnail(Class<? extends CpThumbnail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tqWebtoon = inits.isInitialized("tqWebtoon") ? new com.server.cms.domain.webtoon.QTqWebtoon(forProperty("tqWebtoon")) : null;
    }

}

