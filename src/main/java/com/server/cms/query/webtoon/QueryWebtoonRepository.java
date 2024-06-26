package com.server.cms.query.webtoon;

import com.server.cms.data.request.wevtoon.ReqWebtoonData;
import com.server.cms.domain.webtoon.Webtoon;
import com.server.cms.framework.common.ResponsePageDTO;

public interface QueryWebtoonRepository {

    ResponsePageDTO findByWebtoons(String companyCode, ReqWebtoonData.Search param);

    Webtoon findByWebtoon(String bookCode);

}
