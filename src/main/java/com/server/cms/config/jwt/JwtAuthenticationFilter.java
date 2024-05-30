package com.server.cms.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private static final Pattern BEARER = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
    private static final String tokenHeader = "Authorization";
    private final JWT jwt;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            String authorizationToken = obtainAuthorizationToken(req);
            if(isNotEmpty(authorizationToken)) {
                try {
                    JWT.Claims claims = jwt.verify(authorizationToken);
                    if(log.isDebugEnabled()) {log.debug("JWT RESULT : {}", claims);}

                    if(canRefresh(claims, 1000L * 60L * 5L)) {
                        String refreshToken = "Bearer " + jwt.refreshToken(authorizationToken);
                        res.addHeader(tokenHeader, refreshToken);
                    }

                    String companyCode = claims.getCompanyCode();
                    String userId = claims.getUserId();
                    String name = claims.getName();

                    List<GrantedAuthority> authorities = obtainAuthorities(claims);

                    if(isNotBlank(companyCode) && isNotBlank(name) && authorities.size() > 0)  {
                        JwtAuthenticationToken authentication =
                                new JwtAuthenticationToken(new JwtAuthentication(companyCode, userId, name), null, authorities);
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (Exception e) {
                    log.error("Jwt processing failed: {}", e.getMessage());
                }
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("SecurityContextHolder not populated with security token, as it already contained: '{}'", SecurityContextHolder.getContext().getAuthentication());
            }
        }

        chain.doFilter(req, res);
    }

    private boolean canRefresh(JWT.Claims claims, long refreshRangeMillis) {
        long exp = claims.getExp();
        if(exp > 0) {
            long remain = exp - System.currentTimeMillis();
            return remain < refreshRangeMillis;
        }
        return false;
    }

    private List<GrantedAuthority> obtainAuthorities(JWT.Claims claims) {
        String[] roles = claims.getRoles();
        return roles == null || roles.length == 0 ?
                Collections.emptyList() :
                Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(toList());
    }

    private String obtainAuthorizationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if(token != null) {
            if(log.isDebugEnabled()) { log.debug("JWT TOKEN : {}", token); }

            try {
                token = URLDecoder.decode(token, "UTF-8");
                String[] parts = token.split(" ");
                if (parts.length == 2) {
                    String scheme = parts[0];
                    String credentials = parts[1];
                    return BEARER.matcher(scheme).matches() ? credentials : null;
                }
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

}

