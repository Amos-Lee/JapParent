package com.jos.jap.qq.api;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

public class AbstractSocialApi extends AbstractOAuth2ApiBinding implements SocialApi {
    protected AbstractSocialApi(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    protected AbstractSocialApi(String accessToken, TokenStrategy tokenStrategy) {
        super(accessToken, tokenStrategy);
    }
}
