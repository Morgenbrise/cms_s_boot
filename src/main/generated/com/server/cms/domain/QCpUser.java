package com.server.cms.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCpUser is a Querydsl query type for CpUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCpUser extends EntityPathBase<CpUser> {

    private static final long serialVersionUID = 334384223L;

    public static final QCpUser cpUser = new QCpUser("cpUser");

    public final StringPath companyCode = createString("companyCode");

    public final StringPath cpNm = createString("cpNm");

    public final StringPath email = createString("email");

    public final StringPath id = createString("id");

    public final NumberPath<Long> ind = createNumber("ind", Long.class);

    public final StringPath name = createString("name");

    public final StringPath pw = createString("pw");

    public final StringPath tel = createString("tel");

    public QCpUser(String variable) {
        super(CpUser.class, forVariable(variable));
    }

    public QCpUser(Path<? extends CpUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCpUser(PathMetadata metadata) {
        super(CpUser.class, metadata);
    }

}

