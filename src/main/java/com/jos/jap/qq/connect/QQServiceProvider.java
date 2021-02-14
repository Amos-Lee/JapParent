package com.jos.jap.qq.connect;

import com.jos.jap.qq.api.DefaultQQApi;
import com.jos.jap.qq.api.QQApi;
import com.jos.jap.qq.api.SocialApi;
import org.springframework.social.oauth2.OAuth2Operations;

public class QQServiceProvider extends SocialServiceProvider {
    public QQServiceProvider(OAuth2Operations oauth2Operations) {
        super(oauth2Operations);
    }

    @Override
    public QQApi getApi(String s) {
        return new DefaultQQApi(s);
    }
}
