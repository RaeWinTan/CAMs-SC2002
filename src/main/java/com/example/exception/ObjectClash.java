package com.example.exception;

/** Exception for clash in object name. */
public class ObjectClash  extends RuntimeException {
    public ObjectClash(String objectType, String name) {
        super(objectType + " of name "+ name + " already exists, pick another name");
    }
}
