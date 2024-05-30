package com.server.cms.query.episode;

import com.server.cms.data.request.wevtoon.QTqEpisodeData;
import com.server.cms.framework.common.ResponsePageDTO;

public interface QueryTqWebtoonEpisodeRepository {

    ResponsePageDTO findByWebtoonEpisodes(String companyCode, QTqEpisodeData.Search episode);
    ResponsePageDTO findByEpisodes(String companyCode, QTqEpisodeData.Search episode);

}
