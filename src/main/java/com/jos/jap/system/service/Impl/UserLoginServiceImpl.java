package com.jos.jap.system.service.Impl;

import com.jos.jap.security.token.OpenLoginTokenService;
import com.jos.jap.system.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private OpenLoginTokenService openLoginTokenService;

    public String loginOpenForToken(HttpServletRequest request) {
        return openLoginTokenService.loginForToken(request);
    }
}
