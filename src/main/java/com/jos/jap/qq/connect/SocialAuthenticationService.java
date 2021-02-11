package com.jos.jap.qq.connect;

import com.jos.jap.qq.api.SocialApi;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

public class SocialAuthenticationService extends OAuth2AuthenticationService<SocialApi> {
    public SocialAuthenticationService(OAuth2ConnectionFactory<SocialApi> connectionFactory) {
        super(connectionFactory);
    }
}
