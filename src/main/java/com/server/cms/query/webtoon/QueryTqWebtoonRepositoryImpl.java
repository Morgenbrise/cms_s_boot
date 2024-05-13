package com.server.cms.query.webtoon;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.server.cms.config.querydsl.proxy.OracleQueryDSLRepositorySupport;
import com.server.cms.data.request.wevtoon.QWebtoonData;
import com.server.cms.domain.webtoon.QTqWebtoon;
import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.framework.common.CustomPageRequest;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.type.TqContentStatusType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.querydsl.core.types.dsl.Expressions.stringTemplate;
import static com.server.cms.domain.QContract.contract;
import static com.server.cms.domain.webtoon.QTqWebtoon.tqWebtoon;
import static com.server.cms.domain.webtoon.QWebtoon.webtoon;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class QueryTqWebtoonRepositoryImpl extends OracleQueryDSLRepositorySupport implements QueryTqWebtoonRepository {

    public QueryTqWebtoonRepositoryImpl() {
        super(TqWebtoon.class);
    }

    @Override
    public TqWebtoon findByCpWebtoon(String bookCode) {
        if(isNotEmpty(bookCode)) {
            throw new ContentNotFoundException("도서코드를 정확하지 않습니다.");
        }

        return select(tqWebtoon)
                .from(tqWebtoon)
                .join(tqWebtoon, contract.tqWebtoon)
                .fetchJoin()
                .where(
                        contract.bookCode.eq(bookCode)
                        , tqWebtoon.status.notIn(TqContentStatusType.DELETE)
                )
                .fetchOne();
    }

    @Override
    public ResponsePageDTO findByCpWebtoons(Long cpInd, QWebtoonData.Search param) {
        if(cpInd == null) {
            throw new UserNotFoundException();
        }

        PageRequest pageable = CustomPageRequest.form(param.getPage(), param.getOffset()).of();
        Page<Object> page = applyPagination(pageable, query ->
                select(tqWebtoon)
                        .from(tqWebtoon)
                        .join(tqWebtoon, contract.tqWebtoon)
                        .fetchJoin()
                        .where(
                                contract.cpUser.ind.eq(cpInd),
                                likeTitle(param.getTitle())
                        )
        );

        return ResponsePageDTO.createPageable(page);
    }

    private BooleanExpression likeTitle(String value) {
        return StringUtils.isNotEmpty(value)
                ? stringTemplate("function('REGEXP_REPLACE',{0},{1},{2})", tqWebtoon.title, "[^[:alnum:]]", "")
                .contains(value.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", ""))
                : null;
    }
}