package com.example.controllerlibs;

public enum SortBy {
    CampName("Camp name"),
    ClosingDate("Closing Date"),
    Location("Location");
    
    private String reportFilter;
    SortBy(String reportFilter){
        this.reportFilter = reportFilter;
    }
    public String toString(){
        return this.reportFilter;
    }
    public static SortBy fromString(String s){
        for(SortBy sb:SortBy.values()){
            if(s.equals(sb.toString())) return sb;
        }
        return SortBy.CampName;
    }
}

