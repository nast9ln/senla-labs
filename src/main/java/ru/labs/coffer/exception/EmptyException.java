package ru.labs.coffer.exception;

public class EmptyException extends BaseException {

    public EmptyException() {
    }

    public EmptyException(String message) {
        super(message);
    }

    public EmptyException(String message, Object... args) {
        super(message, args);
    }

}
