package com.jos.jap.qq.api;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.ArrayList;
import java.util.List;

public class DefaultQQApi extends AbstractSocialApi implements QQApi {
    List<GrantedAuthority> auths = new ArrayList<>();

    public DefaultQQApi(String accessToken) {
        super(accessToken);
    }

    public synchronized SocialUser getUser() {
        String userInfoUrl = "https://graph.qq.com/user/get_user_info?oauth_consumer_key={appId}&openid={openId}";
        String result = this.getRestTemplate().getForObject(userInfoUrl, String.class, new Object[]{"123", "123"});
        System.out.println(result);
        return new SocialUser("admin1", "123456", auths);
    }
}
