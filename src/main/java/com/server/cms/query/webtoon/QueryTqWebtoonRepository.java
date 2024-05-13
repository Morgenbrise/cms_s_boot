package com.server.cms.query.webtoon;

import com.server.cms.domain.webtoon.TqWebtoon;

public interface QueryTqWebtoonRepository {

    TqWebtoon findByCpWebtoon(String bookCode);

}
