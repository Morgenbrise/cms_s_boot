package com.server.cms.domain.thumbnail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QThumbnailItem is a Querydsl query type for ThumbnailItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QThumbnailItem extends EntityPathBase<ThumbnailItem> {

    private static final long serialVersionUID = -1602887434L;

    public static final QThumbnailItem thumbnailItem = new QThumbnailItem("thumbnailItem");

    public final StringPath ext = createString("ext");

    public final StringPath imageName = createString("imageName");

    public final NumberPath<Long> ind = createNumber("ind", Long.class);

    public final StringPath path = createString("path");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final EnumPath<com.server.cms.type.YnType> useYn = createEnum("useYn", com.server.cms.type.YnType.class);

    public QThumbnailItem(String variable) {
        super(ThumbnailItem.class, forVariable(variable));
    }

    public QThumbnailItem(Path<? extends ThumbnailItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QThumbnailItem(PathMetadata metadata) {
        super(ThumbnailItem.class, metadata);
    }

}

