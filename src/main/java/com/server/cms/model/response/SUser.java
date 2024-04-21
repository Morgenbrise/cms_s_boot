package com.server.cms.model.response;

import com.server.cms.config.jwt.JWT;
import com.server.cms.domain.CpUser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SUser {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Read {

        private Long idx;

        private String id;

        private String name;

        private String email;

        private String[] roles;

        public static Read from(CpUser entity) {
            return new Read();
        }

        public String newToken(JWT jwt, String[] roles) {
            JWT.Claims claims = JWT.Claims.of(idx, id, name, email, roles);
            return jwt.newToken(claims);
        }

    }

}
