package com.example.view;

import java.util.ArrayList;

import com.example.Page;
import com.example.datastructure.Student;

public class StudentDashboardPromptPage implements IPromptPage<Page>{
    private IPrompt prompt;
    private boolean isCommittee;
    private ArrayList<Page> pages;
    private Page value;
    public StudentDashboardPromptPage(Student student){//here must determine if committee or not
        initialise_pages();
        this.isCommittee = student.getLeading().size() > 0;
        ArrayList<String> os = new ArrayList<String>();
        for(int i = 0; i < this.pages.size(); i++) os.add(this.pages.get(i).getPageView());
        try {
            this.prompt = new PromptOption("What actions you want to do?",os);
        } catch (Exception e) {e.printStackTrace();}
    }

    private void initialise_pages(){
        this.pages.add(Page.ChangePassword);
        /*
        continue adding your pages in the above way here
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
        */
        
        
        
    }


    @Override
    public void perform() {
        this.prompt.startPrompt();
        int i = Integer.valueOf(this.prompt.getResult());
        this.value = this.pages.get(i);
    }

    @Override
    public Page getObject() {
        return this.value;
    }
    
}
