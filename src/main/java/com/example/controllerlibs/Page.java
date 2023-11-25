package com.example.controllerlibs;

//import com.example.view.CampWithdrawalPromptPage;
//import com.example.view.DeleteSuggestion;

//refer to StudentDatshBoardPromptPage to make this mapping
//look at this link you should get where i am going with this:
//https://howtodoinjava.com/java/enum/java-enum-string-example/
public enum Page {
    //shared amonst all users
    Login("Login Page"),
    ChangePassword("Change password"),
    Logout("Logout"),
    ViewEnquiries("View enquiries from students"),//staffDone
    ReplyEnquiry("Reply to enquiries from students"),//staffDone
    //for students
    StudentDashBoard("Student Dashboard"),
    ViewAvailableCampsStudent("View camps open to your faculty"),//d
    WithdrawFromCamp("Withdraw from a camp"),//d
    RegisterAttendeeCamp("Register for a camp as an attendee"),//
    RegisterCommitteeCamp("Register for a camp as a committee"),//
    SubmitEnquiry("Submit an enquiry"),//
    ViewEnquiry("View enquiries that you submitted previously"),//
    EditEnquiry("Edit enquiries that you submitted previously"),//
    ViewAllRegisteredCamps("View all the camps you have registered for"),//
    ViewRepliesToEnquiry("View replies to enquiries you have submitted"),//

    //for camp commmittee member only
    SuggestionCamp("Make a suggestion"),//
    ViewSubmittedSuggestions("View all suggestions you have submitted"),//
    EditSuggestion("Edit suggestions you have submitted"),//
    DeleteSuggestion("Delete suggestions you have submitted"),//

    //for both camp committee and staff
    GenerateStudentReport("Generate camp report with list of students attending each camp"),

    //for staff only
    StaffDashBoard("Staff Dashboard"),
    ViewCampsStaff("View all camps"),
    CreateCamp("Create a camp"),
    EditCamp("Edit an existing camp"),//d
    ViewSuggestions("View suggestions from camp committee members"),//d
    AcceptSuggestion("Choose suggestions to accept"),//d
    GeneratePerformanceReport("Generate a performance report of camp commmittee members");


    private String pageView;
    Page(String pageView){
        this.pageView = pageView;
    }
    public String getPageView(){
        return this.pageView;
    }
}
