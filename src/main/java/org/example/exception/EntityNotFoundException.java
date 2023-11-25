package org.example.exception;

import java.text.MessageFormat;
import java.util.Objects;

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
