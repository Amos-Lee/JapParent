package com.jos.jap.security.config;

import com.jos.jap.security.custom.CustomClientDetailsService;
import com.jos.jap.security.token.OpenLoginTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Configuration
@AutoConfigureAfter({AuthorizationServerEndpointsConfiguration.class, SecurityConfig.class})
public class TokenConfiguration {
    private AuthorizationServerEndpointsConfigurer endpoints = new AuthorizationServerEndpointsConfigurer();

    @Autowired
    private CustomClientDetailsService clientDetailsService;

    @Autowired
    private List<AuthorizationServerConfigurer> configurers = Collections.emptyList();

    @PostConstruct
    public void init() {
        endpoints.setClientDetailsService(clientDetailsService);
        for (AuthorizationServerConfigurer configurer : configurers) {
            try {
                configurer.configure(endpoints);
            } catch (Exception e) {
                throw new IllegalStateException("Cannot configure endpoints", e);
            }
        }
    }

    @Bean
    public OpenLoginTokenService openLoginTokenService() {
        OpenLoginTokenService tokenService = new OpenLoginTokenService(
                endpoints.getTokenGranter(),
                endpoints.getOAuth2RequestFactory()
        );
        return tokenService;
    }
}
