package com.jos.jap.qq.connect;

import com.jos.jap.qq.api.SocialApi;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class SocialApiAdapter implements ApiAdapter<SocialApi> {
    @Override
    public boolean test(SocialApi socialApi) {
        return false;
    }

    @Override
    public void setConnectionValues(SocialApi socialApi, ConnectionValues connectionValues) {

    }

    @Override
    public UserProfile fetchUserProfile(SocialApi socialApi) {
        return null;
    }

    @Override
    public void updateStatus(SocialApi socialApi, String s) {

    }
}
