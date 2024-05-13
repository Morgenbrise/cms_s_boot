package com.server.cms.query.webtoon;

import com.server.cms.data.request.wevtoon.QWebtoonData;
import com.server.cms.domain.webtoon.TqWebtoon;
import com.server.cms.framework.common.ResponsePageDTO;

public interface QueryTqWebtoonRepository {

    TqWebtoon findByCpWebtoon(String bookCode);

    ResponsePageDTO findByCpWebtoons(Long cpInd, QWebtoonData.Search param);

}
