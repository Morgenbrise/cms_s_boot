package com.server.cms.data.response;

import com.server.cms.config.jwt.JWT;
import com.server.cms.domain.CpUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SUser {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Read {

        private String companyCode;

        private String id;

        private String name;

        private String email;

        private String[] roles;

        public static Read from(CpUser entity) {
            return new Read(entity.getCompanyCode(), entity.getId(), entity.getName(), entity.getEmail(), null);
        }

        public String newToken(JWT jwt, String[] roles) {
            JWT.Claims claims = JWT.Claims.of(companyCode, id, name, email, roles);
            return jwt.newToken(claims);
        }

        public String refreshToken(JWT jwt, String token) {
            return jwt.refreshToken(token);
        }

    }

}
