package com.jos.jap.qq.connect;

import com.jos.jap.qq.api.SocialApi;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

public class QQConnectionFactory extends SocialConnectionFactory {
    public QQConnectionFactory(String providerId, OAuth2ServiceProvider<SocialApi> serviceProvider, ApiAdapter<SocialApi> apiAdapter) {
        super(providerId, serviceProvider, apiAdapter);
    }
}
