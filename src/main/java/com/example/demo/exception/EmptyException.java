package com.example.demo.exception;

import java.text.MessageFormat;

public class EmptyException extends RuntimeException{
    public EmptyException(){
    }

    public EmptyException(String message){
        super(message);
    }

    public EmptyException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }
}
