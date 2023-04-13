package com.simplekitchen.project.business.security.jwt.exception;


import org.springframework.security.core.AuthenticationException;

public class JwtUserAuthenticationException extends AuthenticationException {
    public JwtUserAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtUserAuthenticationException(String msg) {
        super(msg);
    }
}
