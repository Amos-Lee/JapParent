package com.jos.jap.security.custom;

import com.jos.jap.security.entity.CustomClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Component
public class CustomClientDetailsService implements ClientDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String name) throws ClientRegistrationException {
        CustomClientDetails clientDetails = new CustomClientDetails();
        clientDetails.setClientId("client");
        clientDetails.setClientSecret(passwordEncoder.encode("123456"));
        clientDetails.setRegisteredRedirectUri(StringUtils.commaDelimitedListToSet("http://localhost:8080/webjars/springfox-swagger-ui/oauth2-redirect.html"));
        clientDetails.setScope(StringUtils.commaDelimitedListToSet("all"));
        clientDetails.setAuthorizedGrantTypes(Arrays.asList("authorization_code", "password","implicit"));
        return clientDetails;
    }
}
