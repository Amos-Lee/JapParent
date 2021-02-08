package com.jos.jap.security.config;

import com.jos.jap.security.social.SocialProviderRepository;
import com.jos.jap.security.social.SocialUserProviderRepository;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SocialAuthenticationServiceLocator;

import javax.servlet.Filter;

public class SpringSocialConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    public void configure(HttpSecurity http) throws Exception {
        ApplicationContext applicationContext = (ApplicationContext) http.getSharedObject(ApplicationContext.class);
        SocialAuthenticationServiceLocator authServiceLocator = (SocialAuthenticationServiceLocator) this.getDependency(applicationContext, SocialAuthenticationServiceLocator.class);
        UsersConnectionRepository usersConnectionRepository = this.getDependency(applicationContext, UsersConnectionRepository.class);
        UserIdSource userIdSource = this.getDependency(applicationContext, UserIdSource.class);
        SocialAuthenticationProvider socialAuthenticationProvider = (SocialAuthenticationProvider)this.getDependency(applicationContext, SocialAuthenticationProvider.class);
        SocialAuthenticationFilter filter = new SocialAuthenticationFilter((AuthenticationManager) http.getSharedObject(AuthenticationManager.class), userIdSource, usersConnectionRepository, authServiceLocator);

        http.authenticationProvider(socialAuthenticationProvider).addFilterBefore((Filter)this.postProcess(filter), AbstractPreAuthenticatedProcessingFilter.class);
    }

    private <T> T getDependency(ApplicationContext applicationContext, Class<T> dependencyType) {
        try {
            T dependency = applicationContext.getBean(dependencyType);
            return dependency;
        } catch (NoSuchBeanDefinitionException var4) {
            throw new IllegalStateException("SpringSocialConfigurer depends on " + dependencyType.getName() + ". No single bean of that type found in application context.", var4);
        }
    }
}
