package com.server.cms.repository;

import com.server.cms.domain.Webtoon;
import com.server.cms.query.webtoon.QueryWebtoonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebtoonRepository extends JpaRepository<Webtoon, Long>, QueryWebtoonRepository {
}
