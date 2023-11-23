package com.example.exception;

public class UnexpectedValueException extends RuntimeException {
     public UnexpectedValueException(String attributeName, String unexpectedValue){
        super("Unexpected " + attributeName + ": " + unexpectedValue);
     }
}
