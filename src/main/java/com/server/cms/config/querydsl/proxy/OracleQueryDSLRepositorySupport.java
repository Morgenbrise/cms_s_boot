package com.server.cms.config.querydsl.proxy;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.cms.config.querydsl.repository.QueryDSLCustomRepository;
import com.server.cms.config.querydsl.repository.QueryDSLCustomRepositorySupport;
import com.server.cms.config.querydsl.repository.SetQueryDSLInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.function.Function;

public class OracleQueryDSLRepositorySupport extends QuerydslRepositorySupport implements QueryDSLCustomRepository, SetQueryDSLInterface {

    private QueryDSLCustomRepositorySupport queryDSLSupper;

    public OracleQueryDSLRepositorySupport(Class<?> domainClass) {
        super(domainClass);
        checkoutQueryDSLRepositorySupport(domainClass);
    }

    @Override
    @PersistenceContext(unitName = "oracleEntityManager")
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
        setSupperEntityManager(entityManager);
    }

    @Override
    public <T> JPAQuery<T> select(Expression<T> expr) {
        return queryDSLSupper.select(expr);
    }

    @Override
    public <T> JPAQuery<T> selectFrom(EntityPath<T> expr) {
        return queryDSLSupper.selectFrom(expr);
    }

    @Override
    public <T> Page<T> applyPagination(Pageable pageable, Function<JPAQueryFactory, JPAQuery> contentQuery) {
        return queryDSLSupper.applyPagination(pageable, contentQuery);
    }

    @Override
    public void checkoutQueryDSLRepositorySupport(Class<?> domainClass) {
        if(queryDSLSupper == null) {
            queryDSLSupper = new QueryDSLCustomRepositorySupport(domainClass);
        }
    }

    @Override
    public void setSupperEntityManager(EntityManager entityManager) {
        queryDSLSupper.setEntityManager(entityManager);
    }

}
