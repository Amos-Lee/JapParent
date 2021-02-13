package com.jos.jap.security.custom;

import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SocialUserDetailsService;

public class CustomSocialAuthenticationProvider extends SocialAuthenticationProvider {
    public CustomSocialAuthenticationProvider(UsersConnectionRepository usersConnectionRepository, SocialUserDetailsService userDetailsService) {
        super(usersConnectionRepository, userDetailsService);
    }
}
