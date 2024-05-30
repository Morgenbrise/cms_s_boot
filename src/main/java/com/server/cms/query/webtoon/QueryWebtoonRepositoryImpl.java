package com.server.cms.query.webtoon;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.server.cms.config.querydsl.proxy.OracleQueryDSLRepositorySupport;
import com.server.cms.data.request.wevtoon.QWebtoonData;
//import com.server.cms.data.response.QSWebtoon_Item;
import com.server.cms.data.response.webtoon.QSWebtoon_Item;
import com.server.cms.domain.webtoon.Webtoon;
import com.server.cms.framework.common.CustomPageRequest;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.error.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static com.querydsl.core.types.dsl.Expressions.stringTemplate;
import static com.server.cms.domain.QContract.contract;
import static com.server.cms.domain.webtoon.QWebtoon.webtoon;

@Slf4j
public class QueryWebtoonRepositoryImpl extends OracleQueryDSLRepositorySupport implements QueryWebtoonRepository {

    public QueryWebtoonRepositoryImpl() {
        super(Webtoon.class);
    }

    @Override
    public ResponsePageDTO findByWebtoons(String companyCode, QWebtoonData.Search param) {
        if(StringUtils.isEmpty(companyCode)) {
            throw new UserNotFoundException();
        }

        log.info("QUERY_WEBTOONS ----> {}", param.toString());
        PageRequest pageable = CustomPageRequest.form(param.getPage(), param.getOffset()).of();
        Page<Webtoon> page = applyPagination(pageable, query ->
                        query
                                .select(new QSWebtoon_Item(webtoon))
                                .from(webtoon)
                                .join(webtoon, contract.webtoon)
                                .fetchJoin()
                                .where(
                                        contract.cpUser.companyCode.eq(companyCode)
//                           likeTitle(null)
                                )
        );

        return ResponsePageDTO.createPageable(page);
    }

    @Override
    public Webtoon findByWebtoon(String bookCode) {

        return null;
    }

    private BooleanExpression likeTitle(String value) {
        return StringUtils.isNotEmpty(value)
                ? stringTemplate("function('REGEXP_REPLACE',{0},{1},{2})", webtoon.title, "[^[:alnum:]]", "")
                    .contains(value.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", ""))
                : null;
    }
}
