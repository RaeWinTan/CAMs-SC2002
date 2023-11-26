package com.example.exception;

/** Exception for lack of permission for a user to use an operation. */
public class InsufficientPermissionException extends RuntimeException {
    public InsufficientPermissionException(String message){
        super("Insufficient Permission: " + message);
    }
}
