package com.jos.jap.core.security.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class JwtTokenExtractor implements TokenExtractor {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenExtractor.class);

    @Override
    public Authentication extract(HttpServletRequest request) {
        String tokenValue = extractToken(request);
        return tokenValue != null ? new PreAuthenticatedAuthenticationToken(tokenValue, "") : null;
    }

    public static String extractToken(HttpServletRequest request) {
        String token = extractHeaderToken(request);
        if (token == null) {
            LOGGER.debug("Token not found in headers. Trying request parameters.");
        }

        return token;
    }

    private static String extractHeaderToken(HttpServletRequest request) {
        Enumeration headers = request.getHeaders("Jwt_Token");
        String value = null;

        while(headers.hasMoreElements()) {
            value = (String)headers.nextElement();
            if (value.toLowerCase().startsWith("Bearer".toLowerCase())) {
                break;
            }
        }

        if (value == null) {
            value = (String)request.getAttribute("Jwt_Token");
        }

        if (StringUtils.isBlank(value)) {
            return null;
        } else {
            String authHeaderValue = value.substring("Bearer".length()).trim();
            request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, value.substring(0, "Bearer".length()).trim());
            int commaIndex = authHeaderValue.indexOf(44);
            if (commaIndex > 0) {
                authHeaderValue = authHeaderValue.substring(0, commaIndex);
            }

            return authHeaderValue;
        }
    }

    public static String extractToken(ServerHttpRequest request) {
        String token = extractHeaderToken(request);
        if (token == null) {
            LOGGER.debug("Token not found in headers and request parameters.");
        }

        return token;
    }

    private static String extractHeaderToken(ServerHttpRequest request) {
        String returnVal = null;
        List<String> values = request.getHeaders().get("Jwt_Token");
        Iterator var3;
        String value;
        if (values != null) {
            var3 = values.iterator();

            while(var3.hasNext()) {
                value = (String)var3.next();
                if (value != null && value.toLowerCase().startsWith("Bearer".toLowerCase())) {
                    returnVal = value;
                    break;
                }
            }
        }

        if (returnVal == null) {
            values = request.getQueryParams().get("Jwt_Token");
            if (values != null) {
                var3 = values.iterator();

                while(var3.hasNext()) {
                    value = (String)var3.next();
                    if (value != null && value.toLowerCase().startsWith("Bearer".toLowerCase())) {
                        returnVal = value;
                        break;
                    }
                }
            }
        }

        if (StringUtils.isBlank(returnVal)) {
            return null;
        } else {
            String authHeaderValue = returnVal.substring("Bearer".length()).trim();
            int commaIndex = authHeaderValue.indexOf(44);
            if (commaIndex > 0) {
                authHeaderValue = authHeaderValue.substring(0, commaIndex);
            }

            return authHeaderValue;
        }
    }
}
