package com.server.cms.repository;

import com.server.cms.domain.webtoon.TqWebtoonEpisode;
import com.server.cms.query.episode.QueryTqWebtoonEpisodeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TqWebtoonEpisodeRepository extends JpaRepository<TqWebtoonEpisode, Long>, QueryTqWebtoonEpisodeRepository {

    @Query("select e from TqWebtoonEpisode e where e.episodeCode = :episodeCode")
    Optional<TqWebtoonEpisode> findByEpisode(String episodeCode);

}
