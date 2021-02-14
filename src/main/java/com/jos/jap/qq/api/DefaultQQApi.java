package com.jos.jap.qq.api;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.ArrayList;
import java.util.List;

public class DefaultQQApi implements QQApi {
    List<GrantedAuthority> auths = new ArrayList<>();

    public synchronized SocialUser getUser() {
        return new SocialUser("admin","123456",auths);
    }
}
