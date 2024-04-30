package com.server.cms.framework.error;

import lombok.Getter;

@Getter
public class ServiceRuntimeException extends RuntimeException {

    private final String messageKey;
    private final String detailKey;
    private final Object[] params;

    public ServiceRuntimeException(String messageKey, String detailKey, Object[] params) {
        this.messageKey = messageKey;
        this.detailKey = detailKey;
        this.params = params;
    }

}
