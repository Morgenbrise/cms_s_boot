package com.server.cms.service;

import com.server.cms.config.security.SecurityUtils;
import com.server.cms.data.request.ReqNotification;
import com.server.cms.framework.common.ResponsePageDTO;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.repository.NotiRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotiService {

    private final NotiRepository notiRepository;

    public ResponsePageDTO findByNotis(ReqNotification param) {
        String companyCode = SecurityUtils.currentCompanyCode();
        if(StringUtils.isEmpty(companyCode)) {
            throw new UserNotFoundException();
        }

        ResponsePageDTO notification = notiRepository.findByNotification(companyCode, param);
        return notification;
    }

}
