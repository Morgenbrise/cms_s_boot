package com.server.cms.query.webtoon;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.server.cms.config.querydsl.proxy.OracleQueryDSLRepositorySupport;
import com.server.cms.domain.webtoon.QTqWebtoon;
import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.framework.error.ContentNotFoundException;
import com.server.cms.type.TqContentStatusType;

import static com.server.cms.domain.QContract.contract;
import static com.server.cms.domain.webtoon.QTqWebtoon.tqWebtoon;
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

}
