package com.example.exception;

/** Exception for unexpected value when setting an attribute. */
public class UnexpectedValueException extends RuntimeException {
     public UnexpectedValueException(String attributeName, String unexpectedValue){
        super("Unexpected " + attributeName + ": " + unexpectedValue);
     }
}
