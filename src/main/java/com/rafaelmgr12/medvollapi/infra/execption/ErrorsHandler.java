package com.rafaelmgr12.medvollapi.infra.execption;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerError400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ErrorDataValidation::new).toList());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handlerErrorBusinessRule(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record ErrorDataValidation(String field, String message) {
     public ErrorDataValidation(FieldError erro){
         this(erro.getField(), erro.getDefaultMessage());
     }

    }
}
