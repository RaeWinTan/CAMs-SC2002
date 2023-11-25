package com.example.view.pages;

import java.util.ArrayList;

import com.example.controllerlibs.Page;
import com.example.datastructure.Student;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.PromptOption;

public class StudentDashboardPromptPage implements IPromptPage<Page>{
    private IPrompt prompt;
    private boolean isCommittee;
    private ArrayList<Page> pages = new ArrayList<>();
    private Page value;
    public StudentDashboardPromptPage(Student student){//here must determine if committee or not
        initialise_pages();
        this.isCommittee = student.getLeading().size() > 0;
        ArrayList<String> os = new ArrayList<String>();
        for(int i = 0; i < this.pages.size(); i++) os.add(this.pages.get(i).getPageView());
        this.prompt = new PromptOption("What actions you want to do?",os);
    }

    private void initialise_pages(){
        this.pages.add(Page.ChangePassword);
        this.pages.add(Page.WithdrawFromCamp);
        this.pages.add(Page.RegisterAttendeeCamp);
        
        this.pages.add(Page.SubmitEnquiry);
        this.pages.add(Page.EditEnquiry);
        this.pages.add(Page.RegisterCommitteeCamp);
        this.pages.add(Page.ViewAllRegisteredCamps);
        this.pages.add(Page.ViewAvailableCampsStudent);
         this.pages.add(Page.ViewEnquiry);
        this.pages.add(Page.ViewRepliesToEnquiry);
        
        if(isCommittee){
            this.pages.add(Page.SuggestionCamp);
            this.pages.add(Page.ViewEnquiriesFromStudent);//same as view enquiry
            this.pages.add(Page.ViewSubmittedSuggestions);//
            this.pages.add(Page.EditSuggestion);
            this.pages.add(Page.DeleteSuggestion);
            this.pages.add(Page.GenerateStudentReport);
        }
        this.pages.add(Page.Logout);
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