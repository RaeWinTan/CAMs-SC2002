package com.example.exception;

public class ObjectNotFoundException extends IllegalOperationException {

    public ObjectNotFoundException(String objectType) {
        super(objectType + " not found.");
    }
    
}
