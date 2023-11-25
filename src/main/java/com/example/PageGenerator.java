package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.controllerlibs.Page;
import com.example.controllerlibs.UserCredentials;
import com.example.controllerlibs.UserType;
import com.example.dataloader.StaffCSVLoader;
import com.example.dataloader.StudentCSVLoader;
import com.example.dataservice.admin.StaffDBService;
import com.example.dataservice.student.StudentDBService;
import com.example.datastore.DataStore;
import com.example.datastore.operator.DataStoreRetrieve;
import com.example.datastore.operator.StaffCampEdit;
import com.example.datastore.operator.UserDataStoreLoad;
import com.example.datastore.operator.UserLoginRetrival;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.GroupName;
import com.example.datastructure.Message;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.datastructure.User;
import com.example.utility.Pair;
import com.example.view.IPromptPage;
import com.example.view.IViewPage;

import com.example.view.TablePromptOption;
import com.example.view.pages.AcceptRejectSuggestionPromptPage;
import com.example.view.pages.CampWithdrawalPromptPage;
import com.example.view.pages.CreateCampPromptPage;
import com.example.view.pages.EditCampPromptPage;
import com.example.view.pages.LoginPromptPage;
import com.example.view.pages.RegisterForCampPromptPage;
import com.example.view.pages.ReplyToEnquiryPromptPage;
import com.example.view.pages.StaffDashboardPromptPage;
import com.example.view.pages.StudentDashboardPromptPage;

public class PageGenerator {
    public static DataStore<Staff> staffDataStore;
	public static DataStore<Student> studentDataStore;
	public static DataStore<Camp> campDataStore;
	
	public static StaffDBService staffDBService = null;
	public static StudentDBService studentDBService = null;
    //shared
    public static User Login(){
        IPromptPage<UserCredentials> loginPage = new LoginPromptPage();
        loginPage.perform();
        UserCredentials uc = loginPage.getObject();
        User user;
        if(uc.getUserType()==UserType.STAFF){
            user = staffDataStore.retrieveData(new UserLoginRetrival<Staff>(uc.getUserId(), uc.getPassword())).get(0);
            if(user instanceof Staff) staffDBService = new StaffDBService((Staff)user);
            
        }else{
            user = studentDataStore.retrieveData(new UserLoginRetrival<Student>(uc.getUserId(), uc.getPassword())).get(0);
            if(user instanceof Student) studentDBService = new StudentDBService((Student)user);
        }
        return user;
    } 

    //staff only
    public static Page StaffDashBoard(){
        IPromptPage<Page> dashboard = new StaffDashboardPromptPage();
        dashboard.perform();
		return dashboard.getObject();
    }

    public static void StaffCreateCamp(Staff staff){
        IPromptPage<Camp> p = new CreateCampPromptPage();
		p.perform();
        Camp newCamp = p.getObject();
        newCamp.setCreatedBy(staff);
        campDataStore.manageData(staffDBService.DSCreateCamp(p.getObject(), staffDataStore));
    }

    public static void ViewCampsStaff(){
        ArrayList<Camp> camps = campDataStore.retrieveData(staffDBService.DSCampRetrival());

        ArrayList<String> headers = new ArrayList<>();
        headers.add("camp name");
        headers.add("description");
        ArrayList<String> camp_names = new ArrayList<>();
        ArrayList<String> camp_details = new ArrayList<>();
        for(Camp c:camps){
            camp_names.add(c.getCampName());
            camp_details.add(c.getDescription());
        }
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camp_names);
        columns.add(camp_details);
        IViewPage p = new TablePromptOption("List of Camps", headers,columns);
        p.perform();
    }
    public static void StaffEditCamp(Staff s){
        Staff staff = staffDataStore.retrieveData(new DataStoreRetrieve<Staff>(s)).get(0);
        ArrayList<Camp> camps = staff.getCampsCreated();
        IPromptPage<Camp> p = new EditCampPromptPage(camps);
        p.perform();
        Camp c = p.getObject();
        campDataStore.manageData(new StaffCampEdit(staff, c));
    }
    public static void ViewEnquiriesStaff(Staff s){
        Staff staff = staffDataStore.retrieveData(new DataStoreRetrieve<Staff>(s)).get(0);
        ArrayList<String> camps = new ArrayList<>();
        ArrayList<String> students = new ArrayList<>();
        ArrayList<String> texts = new ArrayList<>();
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        for(Camp c:staff.getCampsCreated()){
            for(Enquiry e :c.getEnquiries()){
                camps.add(c.getCampName());
                students.add(e.getAuthor().getName());
                texts.add(e.getText());
            }
        }
        columns.add(camps);
        columns.add(students);
        columns.add(texts);
        ArrayList<String> headers = new ArrayList<>();
        headers.add("camp");
        headers.add("student");
        headers.add("text");
        IViewPage display = new TablePromptOption("Enquiries from Students", headers, columns);
        display.perform();
    }

    public static void StaffReplyEnquiry(Staff s){
        Staff staff = staffDataStore.retrieveData(new DataStoreRetrieve<Staff>(s)).get(0);
        IPromptPage<Pair<Enquiry,Message>> p = new ReplyToEnquiryPromptPage(staff, staff.getCampsCreated());
        p.perform();
        staffDBService.DSEnquiryReply(p.getObject());
    }
    public static void ViewSuggestionsStaff(Staff s){
        Staff staff = staffDataStore.retrieveData(new DataStoreRetrieve<Staff>(s)).get(0);
        ArrayList<String> headers = new ArrayList<>();
        headers.add("camp");
        headers.add("student");
        headers.add("suggestion");
        ArrayList<String> camps = new ArrayList<>();
        ArrayList<String> students = new ArrayList<>();
        ArrayList<String> suggestions = new ArrayList<>();
        for(Camp c:staff.getCampsCreated()){
            for(Suggestion sug :c.getSuggestions()){
                camps.add(c.getCampName());
                students.add(sug.getAuthor().getName());
                suggestions.add(sug.getCamp().toString());
            }
        }
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camps);
        columns.add(students);
        columns.add(suggestions);
        IViewPage p = new TablePromptOption("Camp Suggestions Committee members", headers, columns);
        p.perform();
    }
    public static void StaffAcceptSuggestion(Staff s){
        Staff staff = staffDataStore.retrieveData(new DataStoreRetrieve<Staff>(s)).get(0);
        ArrayList<String> headers = new ArrayList<>();
        headers.add("camp");
        headers.add("student");
        headers.add("suggestion");
        ArrayList<String> camps = new ArrayList<>();
        ArrayList<String> students = new ArrayList<>();
        ArrayList<String> suggestions = new ArrayList<>();
        for(Camp c:staff.getCampsCreated()){
            for(Suggestion sug :c.getSuggestions()){
                camps.add(c.getCampName());
                students.add(sug.getAuthor().getName());
                suggestions.add(sug.getCamp().toString());
            }
        }
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camps);
        columns.add(students);
        columns.add(suggestions);
        IPromptPage<Suggestion> p = new AcceptRejectSuggestionPromptPage(staff);
        p.perform();
        staffDBService.DSSuggestionApprove(p.getObject(), studentDataStore, campDataStore);
    }

    // student only
    public static Page StudentDashBoard(Student student){
        IPromptPage<Page> dashboard = new StudentDashboardPromptPage(student);
        dashboard.perform();
		return dashboard.getObject();
    }

    public static void ViewCampStudent(Student student){
        ArrayList<Camp> camps = campDataStore.retrieveData(studentDBService.DSCampRetrival());

        ArrayList<String> headers = new ArrayList<>();
        headers.add("camp name");
        headers.add("description");
        ArrayList<String> camp_names = new ArrayList<>();
        ArrayList<String> camp_details = new ArrayList<>();
        for(Camp c:camps){
            camp_names.add(c.getCampName());
            camp_details.add(c.getDescription());
        }
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camp_names);
        columns.add(camp_details);
        IViewPage p = new TablePromptOption("List of Camps", headers,columns);
        p.perform();
    }

    public static void StudentWithdrawFromCamp(Student s){
        Student student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(s)).get(0);
        IPromptPage<Camp> p = new CampWithdrawalPromptPage(student);
        p.perform();
        campDataStore.manageData(studentDBService.DSQuitCampAsAttendee(p.getObject(), studentDataStore));
    }

    public static void StudentRegisterAsAttendee(Student s){
        ArrayList<Camp> availCamp = campDataStore.retrieveData(studentDBService.DSCampRetrival());
        IPromptPage<Camp> p = new RegisterForCampPromptPage(availCamp);
        p.perform();
        campDataStore.manageData(studentDBService.DSJoinCampAsAttendee(p.getObject(), studentDataStore));
    }



    // Controller

    public static void init(){
        staffDataStore = new DataStore<Staff>();
		studentDataStore = new DataStore<Student>();
		campDataStore = new DataStore<Camp>();
		// Populate userDataStore with Staff and Student objects.
		staffDataStore.manageData(new UserDataStoreLoad<Staff>(new StaffCSVLoader("./src/.data/staff.csv")));
		studentDataStore.manageData(new UserDataStoreLoad<Student>(new StudentCSVLoader("./src/.data/student.csv")));
        
        try {
            DELETETHIS_campinit();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    private static Date DATE(String dateStr) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        return formatter.parse(dateStr);
    }

    private static void DELETETHIS_campinit() throws ParseException{

        Staff staff;
        Camp camp;

        staff = staffDataStore.retrieveData(new UserLoginRetrival<Staff>("ANWIT", "password")).get(0);
        staffDBService = new StaffDBService(staff);

        camp = new Camp();
        camp.setCreatedBy(staff);
        camp.setCampName("NTU Camp - Invisible");
        camp.setDates(new Date[]{DATE("27112023"),DATE("01122023")});
        camp.setClosingDate(DATE("26112000"));
        camp.setTotalSlots(15);
        camp.setCommitteeSlot(10);
        camp.setUserGroup(GroupName.NTU);
        camp.setLocation("NTU");
        camp.setDescription("Camp for NTU");
        camp.setVisibility(false);	
        campDataStore.manageData(staffDBService.DSCreateCamp(camp, staffDataStore));
        
        camp = new Camp();
        camp.setCreatedBy(staff);
        camp.setCampName("NTU Camp - Visible");
        camp.setDates(new Date[]{DATE("27112023"),DATE("01122023")});
        camp.setClosingDate(DATE("26112023"));
        camp.setTotalSlots(15);
        camp.setCommitteeSlot(10);
        camp.setUserGroup(GroupName.NTU);
        camp.setLocation("NTU");
        camp.setDescription("Camp for NTU");
        camp.setVisibility(true);	
        campDataStore.manageData(staffDBService.DSCreateCamp(camp, staffDataStore));

        camp = new Camp();
        camp.setCreatedBy(staff);
        camp.setCampName("SCSE Camp - Visible");
        camp.setDates(new Date[]{DATE("30112023"),DATE("07122023")});
        camp.setClosingDate(DATE("26112023"));
        camp.setTotalSlots(15);
        camp.setCommitteeSlot(10);
        camp.setUserGroup(GroupName.SCSE);
        camp.setLocation("SCSE");
        camp.setDescription("Camp for SCSE");
        camp.setVisibility(true);	
        campDataStore.manageData(staffDBService.DSCreateCamp(camp, staffDataStore));

        staff = staffDataStore.retrieveData(new UserLoginRetrival<Staff>("ARVI", "password")).get(0);
        staffDBService = new StaffDBService(staff);

        camp = new Camp();
        camp.setCreatedBy(staff);
        camp.setCampName("NBS Camp - Visible");
        camp.setDates(new Date[]{DATE("30112023"),DATE("07122023")});
        camp.setClosingDate(DATE("26112023"));
        camp.setTotalSlots(15);
        camp.setCommitteeSlot(10);
        camp.setUserGroup(GroupName.NBS);
        camp.setLocation("NBS");
        camp.setDescription("Camp for NBS");
        camp.setVisibility(true);	
        campDataStore.manageData(staffDBService.DSCreateCamp(camp, staffDataStore));
    }
}
