package com.server.cms.domain.thumbnail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QThumbnail is a Querydsl query type for Thumbnail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QThumbnail extends EntityPathBase<Thumbnail> {

    private static final long serialVersionUID = 1539328835L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QThumbnail thumbnail = new QThumbnail("thumbnail");

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

    //inherited
    public final EnumPath<com.server.cms.type.YnType> useYn = _super.useYn;

    public final com.server.cms.domain.webtoon.QWebtoon webtoon;

    public QThumbnail(String variable) {
        this(Thumbnail.class, forVariable(variable), INITS);
    }

    public QThumbnail(Path<? extends Thumbnail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QThumbnail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QThumbnail(PathMetadata metadata, PathInits inits) {
        this(Thumbnail.class, metadata, inits);
    }

    public QThumbnail(Class<? extends Thumbnail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.webtoon = inits.isInitialized("webtoon") ? new com.server.cms.domain.webtoon.QWebtoon(forProperty("webtoon")) : null;
    }

}

