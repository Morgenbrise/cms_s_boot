package com.server.cms.config.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationManager implements AuthenticationManager {

    private AuthenticationManagerBuilder delegateBuilder;

    private AuthenticationManager delegate;

    private final Object delegateMonitor = new Object();

    public JwtAuthenticationManager(AuthenticationManagerBuilder delegateBuilder) {
        this.delegateBuilder = delegateBuilder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (this.delegate != null) {
            return this.delegate.authenticate(authentication);
        }
        synchronized (this.delegateMonitor) {
            if (this.delegate == null) {
                this.delegate = this.delegateBuilder.getObject();
                this.delegateBuilder = null;
            }
        }
        return this.delegate.authenticate(authentication);
    }

}
