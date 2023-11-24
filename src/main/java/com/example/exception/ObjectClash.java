package com.example.exception;

public class ObjectClash  extends IllegalOperationException{

    public ObjectClash(String objectType, String name) {
        super(objectType + " of name "+ name + " already exists, pick another name");
        //TODO Auto-generated constructor stub
    }
    
}
