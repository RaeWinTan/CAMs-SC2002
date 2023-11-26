package com.example.exception;

/** Exception for prompt option display list is empty. */
public class PromptOptionException  extends RuntimeException{
    public PromptOptionException(String message){
        super(message);
    }
}

