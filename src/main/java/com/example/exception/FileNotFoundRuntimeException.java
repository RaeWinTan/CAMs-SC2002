package com.example.exception;

public class FileNotFoundRuntimeException extends RuntimeException{
    public FileNotFoundRuntimeException(String filePath){
        super("File Not Found: " + filePath);
    }
}
