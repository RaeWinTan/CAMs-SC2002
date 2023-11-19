package com.example.exception;

public class InsufficientPermissionException extends RuntimeException {
    public InsufficientPermissionException(String message){
        super("Insufficient Permission: " + message);
    }
}
