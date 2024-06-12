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

    public final com.server.cms.framework.common.QBaseTimeEntity _super = new com.server.cms.framework.common.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    public final EnumPath<com.server.cms.type.YnType> fixYn = createEnum("fixYn", com.server.cms.type.YnType.class);

    public final NumberPath<Long> ind = createNumber("ind", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regId = _super.regId;

    public final EnumPath<com.server.cms.type.YnType> showYn = createEnum("showYn", com.server.cms.type.YnType.class);

    public final StringPath target = createString("target");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDt = _super.updateDt;

    //inherited
    public final StringPath updateId = _super.updateId;

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

