package com.server.cms.domain.manuscript;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QManuscriptItem is a Querydsl query type for ManuscriptItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QManuscriptItem extends EntityPathBase<ManuscriptItem> {

    private static final long serialVersionUID = 53384570L;

    public static final QManuscriptItem manuscriptItem = new QManuscriptItem("manuscriptItem");

    public final com.server.cms.framework.common.QBaseTimeEntity _super = new com.server.cms.framework.common.QBaseTimeEntity(this);

    public final StringPath ext = createString("ext");

    public final StringPath imageNm = createString("imageNm");

    public final NumberPath<Long> ind = createNumber("ind", Long.class);

    public final StringPath path = createString("path");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regId = _super.regId;

    public final NumberPath<Long> size = createNumber("size", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDt = _super.updateDt;

    //inherited
    public final StringPath updateId = _super.updateId;

    public final EnumPath<com.server.cms.type.YnType> useYn = createEnum("useYn", com.server.cms.type.YnType.class);

    public QManuscriptItem(String variable) {
        super(ManuscriptItem.class, forVariable(variable));
    }

    public QManuscriptItem(Path<? extends ManuscriptItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QManuscriptItem(PathMetadata metadata) {
        super(ManuscriptItem.class, metadata);
    }

}

