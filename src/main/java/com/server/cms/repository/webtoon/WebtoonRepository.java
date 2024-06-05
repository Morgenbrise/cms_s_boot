package com.server.cms.repository.webtoon;

import com.server.cms.domain.webtoon.Webtoon;
import com.server.cms.query.webtoon.QueryWebtoonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebtoonRepository extends JpaRepository<Webtoon, Long>, QueryWebtoonRepository {
}
