package com.ranji.lab.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * ClassName:    JWTToken
 * Package:    com.ranji.lab.util
 * Description: token
 * Datetime:    2020/9/15   9:49 上午
 * Author:   ranji
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}