package com.example.view;

import java.util.ArrayList;

import com.example.utility.Pair;
//dashboard can be the redirections?
public class StudentDashboardPromptPage implements IPromptPage{
    private ArrayList<Pair<String, String>>question_attribute_mapping = new ArrayList<Pair<String, String>>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    private boolean isCommittee;
    public StudentDashboardPromptPage(boolean isCommittee){//here must determine if committee or not
        initialise_question_attribute_mapping();
        this.isCommittee = isCommittee;
    }

    private void initialise_question_attribute_mapping(){
        this.question_attribute_mapping.add(new Pair<String, String>("Change password","changePassword"));
        this.question_attribute_mapping.add(new Pair<String, String>("View Available Camps","viewAvailableCamps"));
        this.question_attribute_mapping.add(new Pair<String, String>("View all registered camps","viewAllRegisteredCamps"));
        this.question_attribute_mapping.add(new Pair<String, String>("Register for camp as attendee","registerAttendeeCamp"));
        
        this.question_attribute_mapping.add(new Pair<String, String>("Enquire about a camp","enquireCamp"));
        this.question_attribute_mapping.add(new Pair<String, String>("View replies to enquiries","viewRepliesToEnquiry"));
        
        this.question_attribute_mapping.add(new Pair<String, String>("Withdraw from a camp","withdrawFromCamp"));
        this.question_attribute_mapping.add(new Pair<String, String>("Register for camp as camp committee member","registerCommitteeCamp"));


        if(isCommittee) this.question_attribute_mapping.add(new Pair<String, String>("Submit suggestions to change camp details","suggestionCamp"));
        if(isCommittee) this.question_attribute_mapping.add(new Pair<String, String>("View enquiries from students","viewEnquiresFromStudent"));
        if(isCommittee) this.question_attribute_mapping.add(new Pair<String, String>("View your submitted suggestions","viewSubmittedSuggestions"));
        if(isCommittee) this.question_attribute_mapping.add(new Pair<String, String>("Edit your submitted suggestions","editSuggestion"));
        if(isCommittee) this.question_attribute_mapping.add(new Pair<String, String>("Delete your submitted suggestions","deleteSuggestion"));
        if(isCommittee) this.question_attribute_mapping.add(new Pair<String, String>("Generate report of attendees for each camp","generateReportAtteedeesForEachCamp"));
        this.question_attribute_mapping.add(new Pair<String, String>("Logout","logout"));
    }
    @Override
    public ArrayList<IPrompt> returnInputs() {
        return this.prompts;
    }

    @Override
    public void prompting() {
        //for(Pair<String,String> i : this.question_attribute_mapping){
            //this.prompts.add(new Prompt());
        //}
        // TODO Auto-generated method stub
        
    }
    
}
