package com.example.exception;

/** Runtime Exception for File Not Found. */
public class FileNotFoundRuntimeException extends RuntimeException{
    public FileNotFoundRuntimeException(String filePath){
        super("File Not Found: " + filePath);
    }
}
