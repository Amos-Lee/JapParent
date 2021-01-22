package com.jos.jap.core.security.filter;

import com.jos.jap.core.security.permission.PublicPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class JwtTokenFilter implements Filter {
    private TokenExtractor tokenExtractor;
    private ResourceServerTokenServices tokenServices;
    private Set<PublicPermission> publicPermissions;
    private static final AntPathMatcher MATCHER = new AntPathMatcher();
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);

    public JwtTokenFilter() {
    }

    public JwtTokenFilter(ResourceServerTokenServices tokenServices, TokenExtractor tokenExtractor, Set<PublicPermission> publicPermissions) {
        this.tokenServices = tokenServices;
        this.tokenExtractor = tokenExtractor;
        this.publicPermissions = publicPermissions;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        Iterator var5 = this.publicPermissions.iterator();

        PublicPermission publicPermission;
        do {
            if (!var5.hasNext()) {
                try {
                    Authentication authentication = this.tokenExtractor.extract(httpRequest);
                    if (authentication == null) {
                        if (this.isAuthenticated()) {
                            LOGGER.debug("Clearing security context.");
                            SecurityContextHolder.clearContext();
                        }
                        LOGGER.debug("No Jwt token in request, will continue chain.");
                        ((HttpServletResponse)response).sendError(401, "No Jwt token in request.");
                        return;
                    }

                    request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE, authentication.getPrincipal());
                    if (authentication instanceof AbstractAuthenticationToken) {
                        AbstractAuthenticationToken needsDetails = (AbstractAuthenticationToken)authentication;
                        needsDetails.setDetails(new OAuth2AuthenticationDetails(httpRequest));
                    }

                    Authentication authResult = this.authenticate(authentication);
                    LOGGER.debug("Authentication success: {}", authResult);
                    SecurityContextHolder.getContext().setAuthentication(authResult);
                    chain.doFilter(request, response);
                } catch (OAuth2Exception var7) {
                    SecurityContextHolder.clearContext();
                    LOGGER.debug("Authentication request failed: ", var7);
                    ((HttpServletResponse)response).sendError(401, "Invalid JWT token.");
                }

                return;
            }

            publicPermission = (PublicPermission)var5.next();
        } while(!MATCHER.match(publicPermission.path, httpRequest.getRequestURI()) || !publicPermission.method.matches(httpRequest.getMethod()));

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    protected Authentication authenticate(Authentication authentication) {
        if (authentication == null) {
            throw new InvalidTokenException("Invalid token (token not found)");
        } else {
            String token = (String)authentication.getPrincipal();
            OAuth2Authentication auth = this.tokenServices.loadAuthentication(token);
            if (auth == null) {
                throw new InvalidTokenException("Invalid token: " + token);
            } else {
                if (authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
                    OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
                    if (!details.equals(auth.getDetails())) {
                        details.setDecodedDetails(auth.getDetails());
                    }
                }

                auth.setDetails(authentication.getDetails());
                auth.setAuthenticated(true);
                return auth;
            }
        }
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
    }
}
