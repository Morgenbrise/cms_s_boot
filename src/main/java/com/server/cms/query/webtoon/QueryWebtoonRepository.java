package com.server.cms.query.webtoon;

import com.server.cms.data.request.QWebtoonData;
import com.server.cms.framework.common.ResponsePageDTO;
import org.springframework.data.domain.Pageable;

public interface QueryWebtoonRepository {

    ResponsePageDTO findByWebtoons(Long cpInd, QWebtoonData.Search param);

}
