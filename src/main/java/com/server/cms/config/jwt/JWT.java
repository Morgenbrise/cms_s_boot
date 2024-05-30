package com.server.cms.config.jwt;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;
import java.util.Date;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

public final class JWT {

    private final String issuer;

    private final String clientSecret;

    private final int expirySeconds;

    private final Algorithm algorithm;

    private final JWTVerifier jwtVerifier;

    public JWT(String issuer, String clientSecret, int expirySeconds) {
        this.issuer = issuer;
        this.clientSecret = clientSecret;
        this.expirySeconds = expirySeconds;
        this.algorithm = Algorithm.HMAC512(clientSecret);
        this.jwtVerifier = com.auth0.jwt.JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
    }

    public String newToken(Claims claims) {
        Date now = new Date();
        JWTCreator.Builder builder = com.auth0.jwt.JWT.create();
        builder.withIssuer(issuer);
        builder.withIssuedAt(now);
        if(expirySeconds > 0) {
            builder.withExpiresAt(new Date(now.getTime() + expirySeconds * 1_000L));
        }
        builder.withClaim("seq", claims.companyCode);
        builder.withClaim("userId", claims.userId);
        builder.withClaim("name", claims.name);
        builder.withArrayClaim("roles", claims.roles);
        return builder.sign(algorithm);
    }

    public String refreshToken(String token) throws JWTVerificationException {
        Claims claims = verify(token);
        claims.eraseIat();
        claims.eraseExp();
        return newToken(claims);
    }

    public Claims verify(String token) throws JWTVerificationException {
        return new Claims(jwtVerifier.verify(token));
    }

    @Getter
    static public class Claims {
        private String companyCode;
        private String userId;
        private String name;
        private String[] roles;
        private Date iat;
        private Date exp;

        private Claims() {}

        Claims(DecodedJWT decodedJWT) {
            Claim companyCode = decodedJWT.getClaim("companyCode");
            if (!companyCode.isNull()) {this.companyCode = companyCode.asString();}
            Claim userId = decodedJWT.getClaim("userId");
            if (!userId.isNull()) {this.userId = userId.asString();}
            Claim name = decodedJWT.getClaim("name");
            if (!name.isNull()) {this.name = name.asString();}
            Claim roles = decodedJWT.getClaim("roles");
            if (!roles.isNull()) {this.roles = roles.asArray(String.class);}

            this.iat = decodedJWT.getIssuedAt();
            this.exp = decodedJWT.getExpiresAt();
        }

        public static Claims of(String companyCode, String userId, String name, String email, String[] roles) {
            Claims claims = new Claims();
            claims.companyCode = companyCode;
            claims.userId = userId;
            claims.name = name;
            claims.roles = roles;
            return claims;
        }

        public long getIat() {
            return isNotEmpty(exp) ? iat.getTime() : -1;
        }

        public long getExp() {
            return isNotEmpty(exp) ? exp.getTime() : -1;
        }

        public void eraseIat() {
            iat = null;
        }

        public void eraseExp() {
            exp = null;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("COMPANY_CODE", companyCode)
                    .append("NAME", name)
                    .append("ROLES", Arrays.toString(roles))
                    .append("IAT", iat)
                    .append("EXP", exp)
                    .toString();
        }
    }
}
