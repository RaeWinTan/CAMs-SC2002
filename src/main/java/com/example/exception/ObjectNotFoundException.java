package com.example.exception;

public class ObjectNotFoundException extends IllegalOperationException {

    public ObjectNotFoundException(String objectType, String location) {
        super(objectType + " not found in " + location + ".");
    }
    
}
