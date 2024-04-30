package com.server.cms.config.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDslConfig {

    @PersistenceContext(unitName = "oracleEntityManager")
    private EntityManager oracleEntityManager;

    @Bean
    public JPAQueryFactory mgmtJpaQueryFactory() {
        return new JPAQueryFactory(oracleEntityManager);
    }

}