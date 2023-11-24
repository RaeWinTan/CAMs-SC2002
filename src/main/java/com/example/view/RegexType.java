package com.example.view;

public enum RegexType {
    DATE("\\d{2}/\\d{2}/\\d{4}"),
    INTEGER("\\d+");
    private String type;
    RegexType(String type){
        this.type = type;
    }
    public String toString(){
        return this.type;
    }
}
/* "\\d+" */