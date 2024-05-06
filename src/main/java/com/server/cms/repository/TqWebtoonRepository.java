package com.server.cms.repository;

import com.server.cms.domain.webtoon.TqWeboon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TqWebtoonRepository extends JpaRepository<TqWeboon, Long> {

    @Query("select h from TqWeboon h where h.showYn = 'Y' and h.webtoon.ind = :parentsInd")
    Optional<TqWeboon> findByIsUseCpWebtoon(@Param("parentsInd") Long parentsInd);

}
