package com.jos.jap.core.security.token;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.endpoint.AbstractEndpoint;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenRequest;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LoginTokenService implements InitializingBean {
    private static final String GRANT_TYPE_IMPLICIT = "implicit";

    private TokenGranter tokenGranter;

    private ClientDetailsService clientDetailsService;

    private OAuth2RequestFactory oAuth2RequestFactory;

    private OAuth2RequestFactory defaultOAuth2RequestFactory;

    private AuthenticationProvider authenticationProvider;

    public LoginTokenService(TokenGranter tokenGranter,
                             ClientDetailsService clientDetailsService,
                             OAuth2RequestFactory oAuth2RequestFactory,
                             AuthenticationProvider authenticationProvider) {
        this.tokenGranter = tokenGranter;
        this.clientDetailsService = clientDetailsService;
        this.oAuth2RequestFactory = oAuth2RequestFactory;
        this.authenticationProvider = authenticationProvider;
    }

    public OAuth2AccessToken loginForToken(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>(8);
        parameters.put("a","b");
        AuthorizationRequest authorizationRequest = getoAuth2RequestFactory().createAuthorizationRequest(parameters);
        authorizationRequest.setApproved(true);
        TokenRequest tokenRequest = getoAuth2RequestFactory().createTokenRequest(authorizationRequest, GRANT_TYPE_IMPLICIT);
        OAuth2Request storedOAuth2Request = getoAuth2RequestFactory().createOAuth2Request(authorizationRequest);
        OAuth2AccessToken token = getTokenGranter().grant(GRANT_TYPE_IMPLICIT, new ImplicitTokenRequest(tokenRequest, storedOAuth2Request));
        return token;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.state(tokenGranter != null, "TokenGranter must be provided");
        Assert.state(clientDetailsService != null, "ClientDetailsService must be provided");
        Assert.state(authenticationProvider != null, "AuthenticationProvider must be provided");
        defaultOAuth2RequestFactory = new DefaultOAuth2RequestFactory(getClientDetailsService());
        if (oAuth2RequestFactory == null) {
            oAuth2RequestFactory = defaultOAuth2RequestFactory;
        }
    }

    public TokenGranter getTokenGranter() {
        return tokenGranter;
    }

    public void setTokenGranter(TokenGranter tokenGranter) {
        this.tokenGranter = tokenGranter;
    }

    public ClientDetailsService getClientDetailsService() {
        return clientDetailsService;
    }

    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    public OAuth2RequestFactory getoAuth2RequestFactory() {
        return oAuth2RequestFactory;
    }

    public void setoAuth2RequestFactory(OAuth2RequestFactory oAuth2RequestFactory) {
        this.oAuth2RequestFactory = oAuth2RequestFactory;
    }

    public OAuth2RequestFactory getDefaultOAuth2RequestFactory() {
        return defaultOAuth2RequestFactory;
    }

    public void setDefaultOAuth2RequestFactory(OAuth2RequestFactory defaultOAuth2RequestFactory) {
        this.defaultOAuth2RequestFactory = defaultOAuth2RequestFactory;
    }

    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }

    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }
}
