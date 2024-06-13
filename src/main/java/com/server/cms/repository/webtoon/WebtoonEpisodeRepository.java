package com.server.cms.repository.webtoon;

import com.server.cms.domain.webtoon.WebtoonEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebtoonEpisodeRepository extends JpaRepository<WebtoonEpisode, Long> {

    @Query("select e from WebtoonEpisode e where e.episodeCode = :episodeCode")
    Optional<WebtoonEpisode> findByEpisode(String episodeCode);

}
