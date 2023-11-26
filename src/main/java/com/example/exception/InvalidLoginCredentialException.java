package com.example.exception;

/** Login fail exception. */
public class InvalidLoginCredentialException extends RuntimeException {
    public InvalidLoginCredentialException(){
        super("Invalid userID or password.");
    }
}
