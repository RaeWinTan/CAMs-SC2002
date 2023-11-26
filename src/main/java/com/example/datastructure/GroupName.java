package com.example.datastructure;

/**
 * GroupName enum for faculty types.
 */
public enum GroupName {
    /** Nanyang Technological University */
    NTU("NTU"),
    /** School of Art, Design and Media */
    ADM("ADM"),
    /** School of Electrical and Electronic Engineering */
    EEE("EEE"),
    /** Nanyang Business School */
    NBS("NBS"),
    /** School of Computer Science and Engineering */
    SCSE("SCSE"),
    /** School of Social Sciences */
	SSS("SSS");
	
    private String groupName;
    GroupName(String groupName){
        this.groupName = groupName;
    }
    public String toString(){
        return this.groupName;
    }
}