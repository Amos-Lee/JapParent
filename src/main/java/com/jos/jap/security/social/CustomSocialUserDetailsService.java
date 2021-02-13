package com.jos.jap.security.social;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.ArrayList;
import java.util.List;

public class CustomSocialUserDetailsService implements SocialUserDetailsService {
    List<GrantedAuthority> auths = new ArrayList<>();

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        return new SocialUser("admin","123456",auths);
    }
}
