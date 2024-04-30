package com.server.cms.query.webtoon;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.server.cms.config.querydsl.proxy.OracleQueryDSLRepositorySupport;
import com.server.cms.data.request.QWebtoonData;
import com.server.cms.data.response.QSWebtoon_Item;
import com.server.cms.data.response.SWebtoon;
import com.server.cms.domain.QContract;
import com.server.cms.domain.QWebtoon;
import com.server.cms.domain.Webtoon;
import com.server.cms.framework.common.CustomPageRequest;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.error.UserNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.querydsl.core.types.dsl.Expressions.stringTemplate;
import static com.server.cms.domain.QContract.contract;
import static com.server.cms.domain.QWebtoon.webtoon;

public class QueryWebtoonRepositoryImpl extends OracleQueryDSLRepositorySupport implements QueryWebtoonRepository {

    public QueryWebtoonRepositoryImpl() {
        super(Webtoon.class);
    }

    @Override
    public ResponsePageDTO findByWebtoons(Long cpInd, QWebtoonData.Search param) {
        if(cpInd == null) {
            throw new UserNotFoundException();
        }

        PageRequest pageable = CustomPageRequest.form(param.getPage(), param.getOffset()).of();
        Page<Webtoon> page = applyPagination(pageable, query ->
                        query
                                .select(new QSWebtoon_Item(webtoon))
                                .from(webtoon)
                                .join(webtoon, contract.webtoon)
                                .fetchJoin()
                                .where(
                                        contract.cpUser.ind.eq(cpInd)
//                           likeTitle(null)
                                )
        );

        return ResponsePageDTO.createPageable(page);
    }

    private BooleanExpression likeTitle(String value) {
        return StringUtils.isNotEmpty(value)
                ? stringTemplate("function('REGEXP_REPLACE',{0},{1},{2})", webtoon.title, "[^[:alnum:]]", "")
                    .contains(value.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", ""))
                : null;
    }
}
