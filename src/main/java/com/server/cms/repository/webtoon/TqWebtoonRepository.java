package com.server.cms.repository.webtoon;

import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.query.webtoon.QueryTqWebtoonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TqWebtoonRepository extends JpaRepository<TqWebtoon, Long>, QueryTqWebtoonRepository {

//    @Query("select h from TqWebtoon h where h.showYn = 'Y' and h.webtoon.ind = :parentsInd")
//    Optional<TqWebtoon> findByIsUseCpWebtoon(@Param("parentsInd") Long parentsInd);

}
