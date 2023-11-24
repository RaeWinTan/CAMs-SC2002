package com.example.datastructure;

public enum GroupName {
    //shared amonst all users
    NTU("NTU"),
    ADM("ADM"),
    EEE("EEE"),
    NBS("NBS"),
    SCSE("SCSE"),
	SSS("SSS");
	
    private String groupName;
    GroupName(String groupName){
        this.groupName = groupName;
    }
    public String toString(){
        return this.groupName;
    }
}