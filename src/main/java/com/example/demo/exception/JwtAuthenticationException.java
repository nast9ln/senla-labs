package com.example.demo.exception;

public class JwtAuthenticationException extends BaseException {

    public JwtAuthenticationException() {
    }

    public JwtAuthenticationException(String message) {
        super(message);
    }

    public JwtAuthenticationException(String message, Object... args) {
        super(message, args);
    }

}
