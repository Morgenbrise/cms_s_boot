package com.server.cms.config.jwt;

import com.server.cms.data.response.ResUser;
import com.server.cms.service.UserService;
import com.server.cms.type.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

import static org.apache.commons.lang3.ClassUtils.isAssignable;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JWT jwt;
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken)authentication;
        return JwtAuthenticationProvider(authenticationToken.authenticationRequest());
    }

    private Authentication JwtAuthenticationProvider(AuthenticationRequest request) {

        try {
            ResUser.Read user = userService.findByLoginUser(request.getId(), request.getPassword());
            JwtAuthenticationToken authenticated =
                    new JwtAuthenticationToken(new JwtAuthentication(user.getCompanyCode(), user.getId(), user.getName())
                                            , null, AuthorityUtils.createAuthorityList(Role.USER.value()));
            String apiToken = user.newToken(jwt, new String[]{Role.USER.value()});

            userService.saveRefreshToken(apiToken);
            authenticated.setDetails(new AuthenticationResult(apiToken, user));
            return authenticated;
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (DataAccessException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return isAssignable(JwtAuthenticationToken.class, authentication);
    }
}
