package com.example.exception;

/** Exception for object not found in operation. */
public class ObjectNotFoundException extends IllegalOperationException {

    public ObjectNotFoundException(String objectType, String location) {
        super(objectType + " not found in " + location + ".");
    }
    
}
