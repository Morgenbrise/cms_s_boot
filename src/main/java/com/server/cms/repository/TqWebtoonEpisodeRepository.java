package com.server.cms.repository;

import com.server.cms.domain.webtoon.TqWebtoonEpisode;
import com.server.cms.query.episode.QueryTqWebtoonEpisodeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TqWebtoonEpisodeRepository extends JpaRepository<TqWebtoonEpisode, Long>, QueryTqWebtoonEpisodeRepository {
}
