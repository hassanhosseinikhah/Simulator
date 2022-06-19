package com.example.simualtor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(RException.class)
    public ResponseEntity<?> resourceNotFoundException(RException ex, WebRequest request) {
     RException rException=new RException("AlarmDto not send to kafka",500);
        return new ResponseEntity<>(rException, HttpStatus.NOT_FOUND);
    }


}
