package com.server.cms.query.webtoon;

import com.server.cms.data.request.wevtoon.ReqWebtoonData;
import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.framework.common.ResponsePageDTO;

public interface QueryTqWebtoonRepository {

    TqWebtoon findByCpWebtoon(String bookCode);
    TqWebtoon findByCpWebtoon(String companyCode, String bookCode);

    ResponsePageDTO findByCpWebtoons(String companyCode, ReqWebtoonData.Search param);

}
