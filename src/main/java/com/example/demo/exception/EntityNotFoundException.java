package com.example.demo.exception;

import java.text.MessageFormat;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }
}
