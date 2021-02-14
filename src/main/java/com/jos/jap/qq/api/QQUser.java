package com.jos.jap.qq.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.social.security.SocialUser;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class QQUser implements QQApi {
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
