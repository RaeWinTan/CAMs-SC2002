package com.example.controllerlibs;

/**
 * ReportFilter enum for filtering generated report by student role.
 */
public enum ReportFilter {
        /** Filter by Attendee only */
        Attendee("Attendee"),
        /** Filter by Committee only */
        Committee("Committee"),
        /** No filer, show all */
        All("All");
        
        private String reportFilter;
        ReportFilter(String reportFilter){
            this.reportFilter = reportFilter;
        }
        public String toString(){
            return this.reportFilter;
        }
}
