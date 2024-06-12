package com.server.cms.query.support;

import com.server.cms.config.querydsl.proxy.OracleQueryDSLRepositorySupport;
import com.server.cms.data.request.ReqNotification;
import com.server.cms.data.response.QResNotification;
import com.server.cms.domain.support.Notification;
import com.server.cms.framework.common.CustomPageRequest;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.type.YnType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static com.server.cms.domain.support.QNotification.notification;

public class QueryNotiRepositoryImpl extends OracleQueryDSLRepositorySupport implements QueryNotiRepository{

    public QueryNotiRepositoryImpl() {
        super(Notification.class);
    }

    @Override
    public ResponsePageDTO findByNotification(String companyCode, ReqNotification param) {
        if(StringUtils.isEmpty(companyCode)) {
            throw new UserNotFoundException();
        }
        PageRequest pageRequest = CustomPageRequest.form(param.getPage(), param.getOffset()).of();

        Page page = applyPagination(pageRequest, query ->
                query.select(new QResNotification(notification.title, notification.content, notification.regDt))
                        .from(notification)
                        .where(
                                notification.target.in("ALL", companyCode)
                                        .and(notification.showYn.eq(YnType.Y))
                        )
                        .orderBy(notification.fixYn.desc(), notification.regDt.desc())
        );

        return ResponsePageDTO.createPageable(page);
    }
}
