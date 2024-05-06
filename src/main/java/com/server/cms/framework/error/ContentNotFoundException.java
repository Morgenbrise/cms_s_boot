package com.server.cms.framework.error;

import com.server.cms.config.utiles.MessageUtils;

public class ContentNotFoundException extends ServiceRuntimeException {

    public static final String MESSAGE_KEY = "error.content.notFound";
    public static final String MESSAGE_DETAIL = "error.content.notFound.details";

    public ContentNotFoundException(String message) {
        super(MESSAGE_KEY, MESSAGE_DETAIL, new Object[]{message});
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
