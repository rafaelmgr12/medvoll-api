package com.rafaelmgr12.medvollapi.infra.execption;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
