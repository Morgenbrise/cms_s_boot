package com.server.cms.config.querydsl.repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.function.Function;

public interface QueryDSLCustomRepository {

    <T> JPAQuery<T> select(Expression<T> expr);

    <T>JPAQuery<T> selectFrom(EntityPath<T> expr);

    <T> Page<T> applyPagination(Pageable pageable, Function<JPAQueryFactory, JPAQuery> contentQuery);

}