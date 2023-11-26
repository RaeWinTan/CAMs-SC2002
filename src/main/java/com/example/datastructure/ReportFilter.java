package com.example.datastructure;

public enum ReportFilter {
        //shared amonst all users
        Attendee("Attendee"),
        Committee("Committee"),
        All("All");
        
        private String reportFilter;
        ReportFilter(String reportFilter){
            this.reportFilter = reportFilter;
        }
        public String toString(){
            return this.reportFilter;
        }
}
