package com.server.cms.config;

import com.server.cms.config.jwt.JWT;
import com.server.cms.config.utiles.MessageUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ServiceConfig {

    @Value(value = "${jwt.token.issuer}") private String issuer;
    @Value(value = "${jwt.token.clientSecret}") private String clientSecret;
    @Value("${jwt.token.expirySeconds}") private int expirySeconds;
    @Value("${jwt.token.header}") private String tokenHeader;

    @Bean
    public JWT jwt() {
        return new JWT(issuer, clientSecret, expirySeconds);
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor(@Qualifier("messageSource") MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource);
    }

    @Bean
    public MessageUtils messageUtils() {
        return MessageUtils.getInstance();
    }
}
