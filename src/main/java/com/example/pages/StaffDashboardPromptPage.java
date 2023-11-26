package com.example.pages;

import java.util.ArrayList;

import com.example.controllerlibs.Page;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.PromptOption;

/**It has all the pages that represent the functionalities offered by the Staff Dashboard.
 * This class implements IPromptPage
 * 
 */
public class StaffDashboardPromptPage implements IPromptPage<Page>{

    private IPrompt prompt;
    private ArrayList<Page> pages = new ArrayList<>();
    private Page value;

    /**Constructor for the class */
    public StaffDashboardPromptPage(){
        initialise_pages();
        ArrayList<String> os = new ArrayList<String>();
        for(int i = 0; i < this.pages.size(); i++) {
            os.add(this.pages.get(i).getPageView());
        }
        this.prompt = new PromptOption("What actions you want to do?",os);
    }

    /**Here the relevant pages for the dashboard are initialised and added to the array 
     * list of pages
     */
    private void initialise_pages(){
        this.pages.add(Page.ChangePassword);
        this.pages.add(Page.ViewCampsStaff);
        this.pages.add(Page.CreateCamp);
        this.pages.add(Page.EditCamp);
        this.pages.add(Page.ViewEnquiries);
        this.pages.add(Page.ReplyEnquiry);
        this.pages.add(Page.ViewSuggestions);
        this.pages.add(Page.AcceptSuggestion);
        this.pages.add(Page.ViewCampsCreatedStaff);
        
        this.pages.add(Page.GenerateStudentReport);
        this.pages.add(Page.PerformanceReport);
        this.pages.add(Page.EnquiryReport);
        this.pages.add(Page.DeleteCamp);
        this.pages.add(Page.Logout);
    }


    /**Here the prompting begins */
    @Override
    public void perform() {
        this.prompt.startPrompt();
        int i = 0;
        for(i = 0; i < this.pages.size(); i++){
            if(this.pages.get(i).getPageView().equals(this.prompt.getResult())) break;
        }
        this.value = this.pages.get(i);
    }

    /**Getter method to return result
     * @return value as a Page object
     */
    @Override
    public Page getObject() {
        return this.value;
    }

}
