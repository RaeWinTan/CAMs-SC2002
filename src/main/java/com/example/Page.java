package com.example;

import com.example.view.CampWithdrawalPromptPage;
import com.example.view.deleteSuggestion;

//refer to StudentDatshBoardPromptPage to make this mapping 
//look at this link you should get where i am going with this:
//https://howtodoinjava.com/java/enum/java-enum-string-example/
public enum Page {
    //shared amonst all users
    ChangePassword("Change password"),
    //ViewAvailableCamps("View Available Camps");
    //students can only see camps open to their faculty

    //for students
    ViewAvailableCampsStudent("View camps open to your faculty");
    WithdrawFromCamp("Withdraw from a camp");
    RegisterAttendeeCamp("Register for a camp as an attendee");
    RegisterCommitteeCamp("Register for a camp as a committee");
    SubmitEnquiry("Submit an enquiry");
    ViewAvailableCamps("View all camps open to you");
    ViewAllRegisteredCamps("View all the camps you have registered for");
    ViewRepliesToEnquiry("View replies to enquiries you have submitted");

    //for camp commmittee member
    SuggestionCamp("Make a suggestion");
    ViewEnquiriesFromStudent("View enquiries from students");
    ViewSubmittedSuggestions("View all suggestions you have submitted");
    EditSuggestion("Edit suggestions you have submitted");
    DeleteSuggestion("Delete suggestions you have submitted");
    GenerateReportAttendeesForEachCamp("Generate a camp report");

    //for staff 
    ViewCampsStaff("View all camps");
    CreateCamp("Create a camp");
    EditCamp("Edit an existing camp");
    ViewEnquiries

    private String pageView;
    Page(String pageView){
        this.pageView = pageView;
    }
    public String getPageView(){
        return this.pageView;
    }
}
