package com.server.cms.framework.error;

import com.server.cms.config.utiles.MessageUtils;

public class UserNotFoundException extends ServiceRuntimeException {

    public static final String MESSAGE_KEY = "error.user.notFound";
    public static final String MESSAGE_DETAIL = "error.user.notFound.details";

    public UserNotFoundException() {
        super(MESSAGE_KEY, MESSAGE_DETAIL, new Object[]{"사용자 정보가 존재하지 않습니다."});
    }

    @Override
    public String getMessage() {
        return MessageUtils.getInstance().getMessage(getDetailKey(), getParams());
    }

    @Override
    public String toString() {
        return MessageUtils.getInstance().getMessage(getMessageKey());
    }

}
