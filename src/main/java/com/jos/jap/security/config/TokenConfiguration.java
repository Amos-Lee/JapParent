package com.jos.jap.security.config;

import com.jos.jap.security.token.OpenLoginTokenService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@AutoConfigureAfter({AuthorizationServerEndpointsConfiguration.class})
public class TokenConfiguration {
    private AuthorizationServerEndpointsConfigurer endpoints = new AuthorizationServerEndpointsConfigurer();

    @Bean
    public OpenLoginTokenService openLoginTokenService() {
        OpenLoginTokenService tokenService = new OpenLoginTokenService(
                endpoints.getTokenGranter(),
                endpoints.getOAuth2RequestFactory()
        );
        return tokenService;
    }
}
