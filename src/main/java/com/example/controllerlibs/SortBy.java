package com.example.controllerlibs;

/**
 * SortBy enum for sorting displayed Camp by various Camp attributes.
 */
public enum SortBy {
    /** Sort by Camp name (alphabetically) */
    CampName("Camp name"),
    /** Sort by last day of registration */
    ClosingDate("Closing Date"),
    /** Sort by Camp location (alphabetically) */
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

