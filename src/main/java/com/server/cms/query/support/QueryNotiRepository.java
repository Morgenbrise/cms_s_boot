package com.server.cms.query.support;

import com.server.cms.data.request.ReqNotification;
import com.server.cms.framework.common.ResponsePageDTO;

public interface QueryNotiRepository {

    ResponsePageDTO findByNotification(String companyCode, ReqNotification param);

}
