package com.server.cms.query.episode;

import com.server.cms.data.request.wevtoon.ReqTqEpisodeData;
import com.server.cms.framework.common.ResponsePageDTO;

public interface QueryTqWebtoonEpisodeRepository {

    ResponsePageDTO findByWebtoonEpisodes(String companyCode, ReqTqEpisodeData.Search episode);
    ResponsePageDTO findByEpisodes(String companyCode, ReqTqEpisodeData.Search episode);

}
