package com.sqw.querydslsample.bean;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFamilyMemberBean is a Querydsl query type for FamilyMemberBean
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFamilyMemberBean extends EntityPathBase<FamilyMemberBean> {

    private static final long serialVersionUID = 1886926975L;

    public static final QFamilyMemberBean familyMemberBean = new QFamilyMemberBean("familyMemberBean");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath relationship = createString("relationship");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QFamilyMemberBean(String variable) {
        super(FamilyMemberBean.class, forVariable(variable));
    }

    public QFamilyMemberBean(Path<? extends FamilyMemberBean> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFamilyMemberBean(PathMetadata metadata) {
        super(FamilyMemberBean.class, metadata);
    }

}

