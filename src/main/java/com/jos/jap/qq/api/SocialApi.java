package com.jos.jap.qq.api;

import org.springframework.social.security.SocialUser;

public interface SocialApi {
    default SocialUser getUser() {
        return null;
    }

    default SocialUser getUser(String providerUserId) {
        return null;
    }
}
