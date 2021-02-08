package com.jos.jap.security.token;

import com.jos.jap.core.constant.ChannelEnum;
import com.jos.jap.core.constant.SocialConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.security.provider.SocialAuthenticationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenLoginTokenService {

    private TokenGranter tokenGranter;

    private OAuth2RequestFactory oAuth2RequestFactory;

    public OpenLoginTokenService(TokenGranter tokenGranter,
                                 OAuth2RequestFactory oAuth2RequestFactory) {
        this.tokenGranter = tokenGranter;
        this.oAuth2RequestFactory = oAuth2RequestFactory;
    }

    private AuthorizationServerEndpointsConfigurer endpoints = new AuthorizationServerEndpointsConfigurer();

    public String loginForToken(HttpServletRequest request) {
//        // 封装请求参数
//        Authentication authRequest = attemptAuthentication(request);
        // 认证
//        Authentication authentication = authenticationProvider.authenticate(authRequest);
//        // 设置登录成功
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 创建token
        OAuth2AccessToken token = createAccessToken();
        return token.toString();
    }

//    protected Authentication attemptAuthentication(HttpServletRequest request) {
//        String providerId = request.getParameter(SocialConstant.PARAM_PROVIDER);
//        String channel = StringUtils.defaultIfBlank(request.getParameter(SocialConstant.PARAM_CHANNEL), ChannelEnum.pc.name());
//        String providerUserId = request.getParameter(SocialConstant.PARAM_OPEN_ID);
//        String openAccessToken = request.getParameter(SocialConstant.PARAM_OPEN_ACCESS_TOKEN); // 用于验证三方用户已授权
//
//        String uniqueProviderId = providerId + "@" + channel;
//
//        ConnectionData connectionData = new ConnectionData(uniqueProviderId, providerUserId, null, null, null, openAccessToken, null, null, null);
//        SocialAuthenticationService<?> authService = getAuthServiceLocator().getAuthenticationService("1234");
//        Connection connection = authService.getConnectionFactory().createConnection(connectionData);
//        connection.sync();
//        return new SocialAuthenticationToken(connection, null);
//    }
//
//    public SocialAuthenticationServiceLocator getAuthServiceLocator() {
//        return this.authServiceLocator;
//    }

    protected OAuth2AccessToken createAccessToken() {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("hello", "hi");
        TokenRequest tokenRequest = null;
        OAuth2AccessToken token = null;

        // 简化模式
        AuthorizationRequest authorizationRequest = this.getOAuth2RequestFactory().createAuthorizationRequest(parameters);
        authorizationRequest.setApproved(true);
        tokenRequest = this.getOAuth2RequestFactory().createTokenRequest(authorizationRequest, "grantType");
        OAuth2Request storedOAuth2Request = this.getOAuth2RequestFactory().createOAuth2Request(authorizationRequest);
        token = this.getTokenGranter().grant("implicit", new ImplicitTokenRequest(tokenRequest, storedOAuth2Request));

        return token;
    }

    protected OAuth2RequestFactory getOAuth2RequestFactory() {
        return oAuth2RequestFactory;
    }

    protected TokenGranter getTokenGranter() {
        return tokenGranter;
    }
}
