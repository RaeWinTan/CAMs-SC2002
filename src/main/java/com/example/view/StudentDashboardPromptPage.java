package com.example.view;

import java.util.ArrayList;

public class StudentDashboardPromptPage implements IPromptPage{
    private ArrayList<String>options = new ArrayList<String>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    private boolean isCommittee;
    public StudentDashboardPromptPage(boolean isCommittee){//here must determine if committee or not
        initialise_question_attribute_mapping();
        this.isCommittee = isCommittee;
    }

    private void initialise_question_attribute_mapping(){
        
        this.options.add("changePassword");
        this.options.add("viewAvailableCamps");
        this.options.add("viewAllRegisteredCamps");
        this.options.add("registerAttendeeCamp");
        
        this.options.add("enquireCamp");
        this.options.add("viewRepliesToEnquiry");
        
        this.options.add("withdrawFromCamp");
        this.options.add("registerCommitteeCamp");
        if(isCommittee) this.options.add("suggestionCamp");
        if(isCommittee) this.options.add("viewEnquiresFromStudent");
        if(isCommittee) this.options.add("viewSubmittedSuggestions");
        if(isCommittee) this.options.add("editSuggestion");
        if(isCommittee) this.options.add("deleteSuggestion");
        if(isCommittee) this.options.add("generateReportAtteedeesForEachCamp");
        this.options.add("logout");
    }
    @Override
    public ArrayList<IPrompt> returnInputs() {
        return this.prompts;
    }

    @Override
    public void perform() {
        try {
            this.prompts.add(new PromptOption("What actions you want to do?", "page",this.options ));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
