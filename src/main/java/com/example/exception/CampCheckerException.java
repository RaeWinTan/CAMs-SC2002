package com.example.exception;

/** Exception for Camp Check fail. */
public class CampCheckerException extends RuntimeException{
    public CampCheckerException(String message){
        super(message);
    }
}
