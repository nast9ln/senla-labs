package org.example.exception;

public class RelativeNotFoundException extends RuntimeException {

    public RelativeNotFoundException() {
    }

    public RelativeNotFoundException(String message) {
        super(message);
    }
}
