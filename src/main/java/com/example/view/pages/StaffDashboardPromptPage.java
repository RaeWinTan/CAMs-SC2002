package com.example.view.pages;

import java.util.ArrayList;

import com.example.controllerlibs.Page;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.PromptOption;


public class StaffDashboardPromptPage implements IPromptPage<Page>{

    private IPrompt prompt;
    private ArrayList<Page> pages = new ArrayList<>();
    private Page value;
    public StaffDashboardPromptPage(){//here must determine if committee or not
        initialise_pages();
        ArrayList<String> os = new ArrayList<String>();
        for(int i = 0; i < this.pages.size(); i++) {
            os.add(this.pages.get(i).getPageView());
        }
        this.prompt = new PromptOption("What actions you want to do?",os);
    }

    private void initialise_pages(){
        this.pages.add(Page.ChangePassword);
        this.pages.add(Page.ViewCampsStaff);
        this.pages.add(Page.CreateCamp);
        this.pages.add(Page.EditCamp);
        this.pages.add(Page.ViewEnquiries);
        this.pages.add(Page.ReplyEnquiry);
        this.pages.add(Page.ViewSuggestions);
        this.pages.add(Page.AcceptSuggestion);
        this.pages.add(Page.GeneratePerformanceReport);
        this.pages.add(Page.GenerateStudentReport);
        this.pages.add(Page.Logout);
    }


    @Override
    public void perform() {
        this.prompt.startPrompt();
        int i = 0;
        for(i = 0; i < this.pages.size(); i++){
            if(this.pages.get(i).getPageView().equals(this.prompt.getResult())) break;
        }
        this.value = this.pages.get(i);
    }

    @Override
    public Page getObject() {
        return this.value;
    }

}
