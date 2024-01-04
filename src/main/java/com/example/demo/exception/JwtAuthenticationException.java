package com.example.demo.exception;

import java.text.MessageFormat;

public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException() {
    }

    public JwtAuthenticationException(String message) {
        super(message);
    }

    public JwtAuthenticationException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }
}
