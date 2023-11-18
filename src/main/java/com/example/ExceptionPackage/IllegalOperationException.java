package com.example.ExceptionPackage;

public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException(String message){
        super(message);
    }
}
