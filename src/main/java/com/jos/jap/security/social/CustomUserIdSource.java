package com.jos.jap.security.social;

import org.springframework.social.UserIdSource;
import org.springframework.stereotype.Component;

@Component
public class CustomUserIdSource implements UserIdSource {
    @Override
    public String getUserId() {
        return null;
    }
}
