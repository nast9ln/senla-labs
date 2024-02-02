package ru.labs.coffer.exception;

public class RelativeNotFoundException extends BaseException {

    public RelativeNotFoundException() {
    }

    public RelativeNotFoundException(String message) {
        super(message);
    }

    public RelativeNotFoundException(String message, Object... args) {
        super(message, args);
    }

}
