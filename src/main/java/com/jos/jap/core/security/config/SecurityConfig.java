package com.jos.jap.core.security.config;

import com.jos.jap.core.security.filter.JwtTokenExtractor;
import com.jos.jap.core.security.filter.JwtTokenFilter;
import com.jos.jap.core.security.permission.PublicPermissionOperationPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.StringUtils;

import javax.servlet.DispatcherType;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    public void configure(WebSecurity web) {
        (web.ignoring().antMatchers(new String[]{"/actuator/**"})).antMatchers(new String[]{"/prometheus"});
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((http
                .httpBasic()
                .disable())
                .csrf()
                .disable())
                .authorizeRequests()
                .antMatchers(new String[]{"/monitor/**"}))
                .permitAll();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration(JwtTokenFilter jwtTokenFilter) {
        //String[] pattern = StringUtils.replace("/hello/*", " ", "").split(",");
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(jwtTokenFilter);
        registration.addUrlPatterns("/hello/*");
        registration.setName("jwtTokenFilter");
        registration.setOrder(2147483647);
        registration.setDispatcherTypes(DispatcherType.REQUEST, new DispatcherType[0]);
        return registration;
    }

    @Bean
    public JwtTokenExtractor jwtTokenExtractor() {
        return new JwtTokenExtractor();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter(PublicPermissionOperationPlugin publicPermissionOperationPlugin, JwtTokenExtractor jwtTokenExtractor) {
        return new JwtTokenFilter(this.tokenServices(), jwtTokenExtractor, publicPermissionOperationPlugin.getPublicPaths());
    }

    private DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(this.tokenStore());
        return defaultTokenServices;
    }

    private TokenStore tokenStore() {
        return new JwtTokenStore(this.accessTokenConverter());
    }

    private JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //converter.setAccessTokenConverter(new CustomTokenConverter());
        converter.setSigningKey("hzero");

        try {
            converter.afterPropertiesSet();
        } catch (Exception var3) {
            LOGGER.warn("error.ResourceServerConfiguration.accessTokenConverter {}", var3.getMessage());
        }

        return converter;
    }
}
