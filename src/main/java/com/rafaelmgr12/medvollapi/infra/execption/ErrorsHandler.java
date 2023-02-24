package com.rafaelmgr12.medvollapi.infra.execption;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerError404(){
        return ResponseEntity.notFound().build();
    }
}
