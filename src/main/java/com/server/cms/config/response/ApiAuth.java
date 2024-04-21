package com.server.cms.config.response;

import lombok.Getter;

@Getter
public class ApiAuth {

    String token;

    public ApiAuth(String token) {
        this.token = token;
    }
}
