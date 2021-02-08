package com.jos.jap.system.controller;

import com.jos.jap.core.util.Results;
import com.jos.jap.system.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/token/open")
    @ResponseBody
    public ResponseEntity<String> loginOpenToken(HttpServletRequest request) {
        String authenticationResult = userLoginService.loginOpenForToken(request);
        return Results.success(authenticationResult);
    }
}
