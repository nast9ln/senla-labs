package ru.labs.coffer.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.labs.coffer.exception.EmptyException;
import ru.labs.coffer.exception.LoginDuplicateException;
import ru.labs.coffer.exception.RelativeNotFoundException;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoginDuplicateException.class)
    public ResponseEntity<String> handleLoginDuplicateException(LoginDuplicateException exception) {
        log.debug(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getLocalizedMessage());
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException exception) {
        log.debug(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<String> handleRelativeNotFoundException(EmptyException exception) {
        log.debug(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(RelativeNotFoundException.class)
    public ResponseEntity<String> handleRelativeNotFoundException(RelativeNotFoundException exception) {
        log.debug(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getLocalizedMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.debug(ex.getMessage());
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

}