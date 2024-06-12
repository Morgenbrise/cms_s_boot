package com.server.cms.domain.support;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotification is a Querydsl query type for Notification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotification extends EntityPathBase<Notification> {

    private static final long serialVersionUID = -2072370447L;

    public static final QNotification notification = new QNotification("notification");

    public final StringPath content = createString("content");

    public final EnumPath<com.server.cms.type.YnType> fixYn = createEnum("fixYn", com.server.cms.type.YnType.class);

    public final NumberPath<Long> ind = createNumber("ind", Long.class);

    public final EnumPath<com.server.cms.type.YnType> showYn = createEnum("showYn", com.server.cms.type.YnType.class);

    public final StringPath target = createString("target");

    public final StringPath title = createString("title");

    public QNotification(String variable) {
        super(Notification.class, forVariable(variable));
    }

    public QNotification(Path<? extends Notification> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotification(PathMetadata metadata) {
        super(Notification.class, metadata);
    }

}

