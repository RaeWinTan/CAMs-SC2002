package com.example.controllerlibs;

/**
 * Page enum for navigating between pages.
 */
public enum Page {
    //shared amonst all users
    /** Login Page for all User*/
    Login("Login Page"),
    /** Change Password page for all User*/
    ChangePassword("Change password"), 
    /** Logout page for all User*/
    Logout("Logout"),
    /** View attendee-submitted enquries page for Staff and Committee Member*/
    ViewEnquiries("View enquiries from students"),
    /** Reply to attendee-submitted enquries page for Staff and Committee Member*/
    ReplyEnquiry("Reply to enquiries from students"),
    //for students

    /** Dashboard page for Student */
    StudentDashBoard("Student Dashboard"),
    /** View all available camp page for Student */
    ViewAvailableCampsStudent("View camps open to your faculty"),
    /** Withdraw from Camp page for all Student */
    WithdrawFromCamp("Withdraw from a camp"),
    /** Register for Camp as Attendee page for Student */
    RegisterAttendeeCamp("Register for a camp as an attendee"),
    /** Register for Camp as Committee Member page for Student */
    RegisterCommitteeCamp("Register for a camp as a committee"),
    /** Submit Enquiry page for Attendee */
    SubmitEnquiry("Submit an enquiry"),
    /** View self-submitted Enquiry page for Attendee */
    ViewEnquiry("View enquiries that you submitted previously"),
    /** Edit self-submitted Enquiry page for Attendee */
    EditEnquiry("Edit enquiries that you submitted previously"),
    /** View all registered Camps page for Student */
    ViewAllRegisteredCamps("View all the camps you have registered for"),
    /** View Reply to self-submitted Enqury page for Attendee */
    ViewRepliesToEnquiry("View replies to enquiries you have submitted"),

    //for camp commmittee member only
    /** Create Suggestion page for Committee Member */
    CreateSuggestion("Create a suggestion"),
    /** View self-submitted Suggestion page for Committee Member */
    ViewSubmittedSuggestions("View all suggestions you have submitted"),
    /** Edit self-submitted Suggestion page for Committee Member */
    EditSuggestion("Edit suggestions you have submitted"),
    /** Delete self-submitted Suggestion page for Committee Member */
    DeleteSuggestion("Delete suggestions you have submitted"),

    /** Generate Participant Report page for Staff and Committee Member */
    GenerateStudentReport("Generate camp report with list of students attending each camp"),

    //for staff only
    /** Dashboard page for Staff */
    StaffDashBoard("Staff Dashboard"),
    /** View all Camp page for Staff */
    ViewCampsStaff("View all camps"),
    /** Create Camp page for Staff */
    CreateCamp("Create a camp"),
    /** Edit self-created camp page for Staff */
    EditCamp("Edit an existing camp"),
    /** View Committee-Member-submitted Suggestion page for Staff */
    ViewSuggestions("View suggestions from camp committee members"),
    /** Accept Committee-Member-submitted Suggestion page for Staff */
    AcceptSuggestion("Choose suggestions to accept"),
    /** View self-created Camp page for Staff */
    ViewCampsCreatedStaff("Camps created"),
    /** Generate Performance Report page for Staff */
    PerformanceReport("Generate Committee performance Report"),
    /** Generate Enquiry Report page for Staff */
    EnquiryReport("Generate Enquiry"),
    /** Delete Camp page for Staff */
    DeleteCamp("Delete Camp");

    private String pageView;
    Page(String pageView){
        this.pageView = pageView;
    }
    public String getPageView(){
        return this.pageView;
    }
}
