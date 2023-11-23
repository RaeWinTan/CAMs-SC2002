package com.example.datastructure;

public enum GroupName {
    //shared amonst all users
    NTU("NTU"),
    SCSE("SCSE"),
	ADM("ADM"),
	EEE("EEE"),
	NMS("NMS"),
	SSS("SSS");
    
    private String groupName;
    GroupName(String groupName){
        this.groupName = groupName;
    }
    public String toString(){
        return this.groupName;
    }
}