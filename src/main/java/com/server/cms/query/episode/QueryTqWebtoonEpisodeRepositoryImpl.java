package com.server.cms.query.episode;

import com.server.cms.config.querydsl.proxy.OracleQueryDSLRepositorySupport;
import com.server.cms.data.request.wevtoon.QTqEpisodeData;
import com.server.cms.data.response.webtoon.QSEpisode_Item;
import com.server.cms.data.response.webtoon.QSEpisode_WebtoonEpisodeItem;
import com.server.cms.domain.webtoon.TqWebtoonEpisode;
import com.server.cms.framework.common.CustomPageRequest;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.framework.error.UserNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.server.cms.domain.QContract.contract;
import static com.server.cms.domain.webtoon.QTqWebtoon.tqWebtoon;
import static com.server.cms.domain.webtoon.QTqWebtoonEpisode.tqWebtoonEpisode;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class QueryTqWebtoonEpisodeRepositoryImpl extends OracleQueryDSLRepositorySupport implements QueryTqWebtoonEpisodeRepository {

    public QueryTqWebtoonEpisodeRepositoryImpl() {
        super(TqWebtoonEpisode.class);
    }

    @Override
    public ResponsePageDTO findByWebtoonEpisodes(Long userInd, QTqEpisodeData.Search episode) {
        if(userInd == null) {
            throw new UserNotFoundException();
        }

        if(isEmpty(episode.getBookCode())) {
            throw  new ContentNotFoundException("도서 코드를 확인 할 수 없습니다.");
        }

        PageRequest pageable = CustomPageRequest.form(episode.getPage(), episode.getOffset()).of();

        Page<Object> page = applyPagination(pageable, query ->
                query.select(new QSEpisode_WebtoonEpisodeItem(
                                tqWebtoonEpisode.episodeCode
                                , tqWebtoonEpisode.title
                                , tqWebtoonEpisode.episodeNum
                                , tqWebtoonEpisode.order
                                , tqWebtoonEpisode.price
                                , tqWebtoonEpisode.path.concat(tqWebtoonEpisode.thumbnailName)
                                , tqWebtoonEpisode.status
                        ))
                        .from(tqWebtoonEpisode)
                        .join(tqWebtoonEpisode.tqWebtoon, tqWebtoon)
                        .fetchJoin()
                        .join(contract.tqWebtoon, tqWebtoon)
                        .fetchJoin()
                        .where(
                                contract.cpUser.ind.eq(userInd),
                                contract.bookCode.eq(episode.getBookCode())
                        )
        );

        return ResponsePageDTO.createPageable(page);
    }

    @Override
    public ResponsePageDTO findByEpisodes(Long userInd,QTqEpisodeData.Search episode) {
        if(userInd == null) {
            throw new UserNotFoundException();
        }

        PageRequest pageable = CustomPageRequest.form(episode.getPage(), episode.getOffset()).of();

        Page<Object> page = applyPagination(pageable, query ->
                query.select(new QSEpisode_Item(
                                tqWebtoonEpisode.episodeCode
                                , tqWebtoon.title
                                , tqWebtoonEpisode.title
                                , tqWebtoonEpisode.episodeNum
                                , tqWebtoonEpisode.order
                                , tqWebtoonEpisode.price
                                , tqWebtoonEpisode.thumbnailName
                                , tqWebtoonEpisode.status
                        ))
                        .from(tqWebtoonEpisode)
                        .join(tqWebtoonEpisode.tqWebtoon, tqWebtoon)
                        .fetchJoin()
                        .join(contract.tqWebtoon, tqWebtoon)
                        .fetchJoin()
                        .where(
                                contract.cpUser.ind.eq(userInd),
                                tqWebtoonEpisode.updateDt.between(converterDate(episode.getStDate(), "start"), converterDate(episode.getEnDate(), "end"))
                        )
        );

        return ResponsePageDTO.createPageable(page);
    }

    private LocalDateTime converterDate(String value, String type) {
        if(isEmpty(value)) {
            return LocalDateTime.now().minusMonths(1);
        }

        String s = value.replaceAll("[^0-9]", "");
        if(s.length() <= 8) {
            return "start".equals(type) ? startDate(s) : endDate(s);
        }

        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private LocalDateTime startDate(String s) {
        String yarn = s.substring(0, 4);
        String month = s.substring(4, 6);
        String dayOfMonth = s.substring(6, 8);
        return LocalDateTime.of(Integer.parseInt(yarn), Integer.parseInt(month), Integer.parseInt(dayOfMonth), 0, 0, 0);
    }

    private LocalDateTime endDate(String s) {
        String yarn = s.substring(0, 4);
        String month = s.substring(4, 6);
        String dayOfMonth = s.substring(6, 8);
        return LocalDateTime.of(Integer.parseInt(yarn), Integer.parseInt(month), Integer.parseInt(dayOfMonth), 23, 59, 59);
    }

}
