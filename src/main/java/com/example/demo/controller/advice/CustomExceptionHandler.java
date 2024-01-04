package com.example.demo.controller.advice;

import com.example.demo.exception.EmptyException;
import com.example.demo.exception.RelativeNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends DefaultHandlerExceptionResolver {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException exception) {
        log.info(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<String> handleRelativeNotFoundException(EmptyException exception) {
        log.info(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(RelativeNotFoundException.class)
    public ResponseEntity<String> handleRelativeNotFoundException(RelativeNotFoundException exception) {
        log.info(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getLocalizedMessage());
    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<String> handleDataIntegrityViolationException(RelativeNotFoundException exception) {
//        log.info(exception.getMessage());
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(exception.getLocalizedMessage());
//    }

//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException exception) {
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body(exception.getLocalizedMessage());
//    }

//    @ExceptionHandler(JwtAuthenticationException.class)
//    public ResponseEntity<String> handleJwtException(JwtAuthenticationException exception) {
//        return ResponseEntity
//                .status(HttpStatus.FORBIDDEN)
//                .body(exception.getLocalizedMessage());
//    }

    @Override
    protected ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.info(ex.getMessage());
        return super.handleMethodArgumentNotValidException(ex, request, response, handler);
    }
}