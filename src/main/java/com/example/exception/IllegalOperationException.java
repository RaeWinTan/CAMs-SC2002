package com.example.exception;

/** Exception for Illegal usage of operations. */
public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException(String message){
        super(message);
    }
}
