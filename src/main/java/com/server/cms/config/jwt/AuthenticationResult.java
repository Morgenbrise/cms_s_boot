package com.server.cms.config.jwt;

import com.server.cms.model.response.SUser;

public class AuthenticationResult {

    private final String apiToken;

    private final SUser.Read user;

    public AuthenticationResult(String apiToken, SUser.Read user) {
        this.apiToken = apiToken;
        this.user = user;
    }
    public String getApiToken() {
        return apiToken;
    }
    public SUser.Read getUser() {
        return user;
    }

}
