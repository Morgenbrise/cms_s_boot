package com.server.cms.config.security;

import com.server.cms.config.handler.EntryPointUnauthorizedHandler;
import com.server.cms.config.handler.WebAccessDeniedHandler;
import com.server.cms.config.jwt.JWT;
import com.server.cms.config.jwt.JwtAuthenticationFilter;
import com.server.cms.config.jwt.JwtAuthenticationManager;
import com.server.cms.config.jwt.JwtAuthenticationProvider;
import com.server.cms.service.UserService;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JWT jwt;
    private final WebAccessDeniedHandler webAccessDeniedHandler;
    private final EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider(JWT jwt, UserService userService) {
        return new JwtAuthenticationProvider(jwt, userService);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationTokenFilter(JWT jwt) {
        return new JwtAuthenticationFilter(jwt);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        http.authenticationManager(authenticationManager);

        http.httpBasic(HttpBasicConfigurer::disable);
        http.csrf(CsrfConfigurer::disable).csrf(Customizer.withDefaults());

        http
                .authorizeHttpRequests(request -> request.dispatcherTypeMatchers(DispatcherType.FORWARD)
                                                    .permitAll()
                                                    .requestMatchers("/swagger-ui/**", "/swagger-resources/**","/v3/api-docs/**", "/api-docs/**").permitAll()
                                                    .requestMatchers("/auth/**").permitAll()
//                                                    .requestMatchers("/api/**").authenticated()
                                                    .anyRequest().authenticated()
                )

                .exceptionHandling(authentication ->
                        authentication.accessDeniedHandler(webAccessDeniedHandler)
                                      .authenticationEntryPoint(entryPointUnauthorizedHandler)

                )

                .formLogin(FormLoginConfigurer::disable)
                .addFilterBefore(jwtAuthenticationTokenFilter(jwt), UsernamePasswordAuthenticationFilter.class)

                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                                .invalidSessionUrl("/"));
        return http.build();
    }

}
