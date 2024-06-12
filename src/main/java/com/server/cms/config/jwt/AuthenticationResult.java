package com.server.cms.config.jwt;

import com.server.cms.data.response.ResUser;
import lombok.Getter;

@Getter
public class AuthenticationResult {

    private final String apiToken;

    private String companyCode;

    private final String userId;

    private final String userName;

    private final String email;

    public AuthenticationResult(String apiToken, ResUser.Read user) {
        this.apiToken = apiToken;
        this.companyCode = user.getCompanyCode();
        this.userId = user.getId();
        this.userName = user.getName();
        this.email = user.getEmail();
    }

    public String getApiToken() {
        return apiToken;
    }

}
