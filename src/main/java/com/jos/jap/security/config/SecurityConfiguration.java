package com.jos.jap.security.config;

import com.jos.jap.qq.config.QQSocialBuilder;
import com.jos.jap.qq.connect.SocialConnectionFactory;
import com.jos.jap.security.custom.CustomAuthenticationProvider;
import com.jos.jap.security.custom.CustomSocialAuthenticationProvider;
import com.jos.jap.security.social.CustomSocialUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.provider.OAuth2AuthenticationService;
import org.springframework.social.security.provider.SocialAuthenticationService;

@Configuration
public class SecurityConfiguration {
    @Autowired
    private QQSocialBuilder qqSocialBuilder;

    @Bean
    @ConditionalOnMissingBean(CustomAuthenticationProvider.class)
    public CustomAuthenticationProvider authenticationProvider () {
        CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
        return provider;
    }

    @Bean
    public SocialAuthenticationServiceLocator socialAuthenticationServiceLocator() {
        SocialAuthenticationServiceRegistry socialAuthenticationServiceLocator = new SocialAuthenticationServiceRegistry();
        SocialConnectionFactory connectionFactory = qqSocialBuilder.buildConnectionFactory();
        SocialAuthenticationService socialAuthenticationService = new OAuth2AuthenticationService(connectionFactory);
        socialAuthenticationServiceLocator.addAuthenticationService(socialAuthenticationService);
        return socialAuthenticationServiceLocator;
    }

    @Bean
    @ConditionalOnMissingBean(SocialUserDetailsService.class)
    public SocialUserDetailsService socialUserDetailsService() {
        return new CustomSocialUserDetailsService();
    }

    @Bean
    @ConditionalOnMissingBean(SocialAuthenticationProvider.class)
    public SocialAuthenticationProvider socialAuthenticationProvider(UsersConnectionRepository usersConnectionRepository,
                                                                     SocialUserDetailsService socialUserDetailsService) {
        return new CustomSocialAuthenticationProvider(usersConnectionRepository, socialUserDetailsService);
    }
}
