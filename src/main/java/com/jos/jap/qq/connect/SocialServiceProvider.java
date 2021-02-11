package com.jos.jap.qq.connect;

import com.jos.jap.qq.api.SocialApi;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;

public class SocialServiceProvider extends AbstractOAuth2ServiceProvider<SocialApi> {
    public SocialServiceProvider(OAuth2Operations oauth2Operations) {
        super(oauth2Operations);
    }

    @Override
    public SocialApi getApi(String s) {
        return null;
    }
}
