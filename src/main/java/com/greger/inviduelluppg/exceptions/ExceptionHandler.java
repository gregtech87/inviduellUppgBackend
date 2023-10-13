package com.greger.inviduelluppg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<EntityError> exeptionHadler(EntityNotFoundException entityNotFoundException) {
        EntityError error = new EntityError();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(entityNotFoundException.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<EntityError> exeptionHadler(Exception exception) {
        EntityError error = new EntityError();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Only hole numbers as path variabel. || Path variabel not compatible with your request. || Invalid character in attribute.");
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
