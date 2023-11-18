package com.example.exception;

public class InvalidLoginCredentialException extends RuntimeException {
    public InvalidLoginCredentialException(){
        super("Invalid userID or password.");
    }
}
