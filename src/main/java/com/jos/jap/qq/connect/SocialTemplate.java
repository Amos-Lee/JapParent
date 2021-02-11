package com.jos.jap.qq.connect;

import org.springframework.social.oauth2.OAuth2Template;

public class SocialTemplate extends OAuth2Template {
    public SocialTemplate(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
    }
}
