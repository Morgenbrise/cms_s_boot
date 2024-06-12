package com.server.cms.query.support;

import com.server.cms.config.querydsl.proxy.OracleQueryDSLRepositorySupport;
import com.server.cms.data.request.ReqNotification;
import com.server.cms.domain.support.Notification;
import com.server.cms.framework.common.ResponsePageDTO;

public class QueryNotiRepositoryImpl extends OracleQueryDSLRepositorySupport implements QueryNotiRepository{

    public QueryNotiRepositoryImpl() {
        super(Notification.class);
    }

    @Override
    public ResponsePageDTO findByNotification(String companyCode, ReqNotification param) {

        return null;
    }
}
