package com.server.cms.config.querydsl.repository;

import jakarta.persistence.EntityManager;

public interface SetQueryDSLInterface {

    void checkoutQueryDSLRepositorySupport(Class<?> domainClass);

    void setSupperEntityManager(EntityManager entityManager);

}
