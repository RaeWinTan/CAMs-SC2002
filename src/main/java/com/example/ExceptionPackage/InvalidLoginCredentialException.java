package com.example.ExceptionPackage;

public class InvalidLoginCredentialException extends RuntimeException {
    public InvalidLoginCredentialException(){
        super("Invalid userID or password.");
    }
}
