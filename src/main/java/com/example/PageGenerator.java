package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

import com.example.controllerlibs.Page;
import com.example.controllerlibs.ReportFilter;
import com.example.controllerlibs.SortBy;
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
import com.example.datastructure.CampMember;
import com.example.datastructure.Enquiry;
import com.example.datastructure.GroupName;
import com.example.datastructure.Message;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.datastructure.User;
import com.example.utility.Pair;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.IViewPage;
import com.example.view.PromptOption;
import com.example.view.TablePromptOption;


import com.example.view.pages.CampEnquiryPromptPage;
import com.example.view.pages.CampWithdrawalPromptPage;
import com.example.view.pages.ChangePasswordPromptPage;
import com.example.view.pages.CreateCampPromptPage;
import com.example.view.pages.CreateSuggestionPromptPage;
import com.example.view.pages.DeleteSuggestionPromptPage;
import com.example.view.pages.EditEnquiryPromptPage;
import com.example.view.pages.EditSuggestionPromptPage;
import com.example.view.pages.GenerateStudentReportPromptPage;
import com.example.view.pages.AcceptRejectSuggestionPromptPage;
import com.example.view.pages.EditCampPromptPage;

import com.example.view.pages.LoginPromptPage;
import com.example.view.pages.RegisterForCampPromptPage;
import com.example.view.pages.ReplyToEnquiryPromptPage;
import com.example.view.pages.StaffDashboardPromptPage;
import com.example.view.pages.StudentDashboardPromptPage;

public class PageGenerator {
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static DataStore<Staff> staffDataStore;
	public static DataStore<Student> studentDataStore;
	public static DataStore<Camp> campDataStore;
	
	public static StaffDBService staffDBService = null;
	public static StudentDBService studentDBService = null;
    public static ArrayList<SortBy> sortBy = new ArrayList<>(Arrays.asList(SortBy.values()));
    public static ArrayList<String> sortyBy_str = new ArrayList<>();
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

    public static void ChangePassword(User user){
        IPromptPage<User> p = new ChangePasswordPromptPage(user);
        p.perform();
        if (user instanceof Staff){
            staffDataStore.manageData(staffDBService.DBUserChangePassword((Staff)p.getObject()));
        } else if (user instanceof Student){
            studentDataStore.manageData(studentDBService.DBUserChangePassword((Student)p.getObject()));
        }
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
    public static void ViewCampsCreatedStaff(Staff s){
        Staff staff = staffDataStore.retrieveData(new DataStoreRetrieve<Staff>(s)).get(0);
        ArrayList<Camp> camps = staff.getCampsCreated();
        ArrayList<String> headers = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        headers.add("camp name");
        headers.add("closing date");
        headers.add("start date");
        headers.add("end date");
        headers.add("open to");
        headers.add("location");
        headers.add("total slots");
        headers.add("committee slots");
        headers.add("description");
        headers.add("visibility");
        
        ArrayList<String> camp_names = new ArrayList<>();
        ArrayList<String> closing_dates = new ArrayList<>();
        ArrayList<String> start_dates = new ArrayList<>();
        ArrayList<String> end_dates = new ArrayList<>();
        ArrayList<String> openTos = new ArrayList<>();
        ArrayList<String> locations = new ArrayList<>();
        ArrayList<String> totalSlots = new ArrayList<>();
        ArrayList<String> committeeSlots = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        ArrayList<String> visibilitys = new ArrayList<>();
        sortCamps(camps);
        for(Camp c:camps){
            camp_names.add(c.getCampName());
            closing_dates.add(sdf.format(c.getClosingDate()));
            start_dates.add(sdf.format(c.getDates()[0]));
            end_dates.add(sdf.format(c.getDates()[1]));
            openTos.add(c.getUserGroup().toString());
            locations.add(c.getLocation());
            totalSlots.add(c.getTotalSlots()+"");
            committeeSlots.add(c.getCommitteeSlot()+"");
            descriptions.add(c.getDescription());
            visibilitys.add(c.getVisibility()+"");
        }
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camp_names);
        columns.add(closing_dates);
        columns.add(start_dates);
        columns.add(end_dates);
        columns.add(openTos);
        columns.add(locations);
        columns.add(totalSlots);
        columns.add(committeeSlots);
        columns.add(descriptions);
        columns.add(visibilitys);
        IViewPage p = new TablePromptOption("List of Camps", headers,columns);
        p.perform();
    }
    public static void ViewCampsStaff(){
        ArrayList<Camp> camps = campDataStore.retrieveData(staffDBService.DSCampRetrival());

        ArrayList<String> headers = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        headers.add("camp name");
        headers.add("closing date");
        headers.add("start date");
        headers.add("end date");
        headers.add("open to");
        headers.add("location");
        headers.add("total slots");
        headers.add("committee slots");
        headers.add("description");
        headers.add("visibility");
        
        ArrayList<String> camp_names = new ArrayList<>();
        ArrayList<String> closing_dates = new ArrayList<>();
        ArrayList<String> start_dates = new ArrayList<>();
        ArrayList<String> end_dates = new ArrayList<>();
        ArrayList<String> openTos = new ArrayList<>();
        ArrayList<String> locations = new ArrayList<>();
        ArrayList<String> totalSlots = new ArrayList<>();
        ArrayList<String> committeeSlots = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        ArrayList<String> visibilitys = new ArrayList<>();
        sortCamps(camps);
        for(Camp c:camps){
            camp_names.add(c.getCampName());
            closing_dates.add(sdf.format(c.getClosingDate()));
            start_dates.add(sdf.format(c.getDates()[0]));
            end_dates.add(sdf.format(c.getDates()[1]));
            openTos.add(c.getUserGroup().toString());
            locations.add(c.getLocation());
            totalSlots.add(c.getTotalSlots()+"");
            committeeSlots.add(c.getCommitteeSlot()+"");
            descriptions.add(c.getDescription());
            visibilitys.add(c.getVisibility()+"");
        }
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camp_names);
        columns.add(closing_dates);
        columns.add(start_dates);
        columns.add(end_dates);
        columns.add(openTos);
        columns.add(locations);
        columns.add(totalSlots);
        columns.add(committeeSlots);
        columns.add(descriptions);
        columns.add(visibilitys);
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
        campDataStore.manageData(staffDBService.DSEnquiryReply(p.getObject()));
    }
    public static void ViewSuggestionsStaff(Staff s){
        Staff staff = staffDataStore.retrieveData(new DataStoreRetrieve<Staff>(s)).get(0);
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Camp");
        headers.add("Student");
        headers.add("Suggestion");
        headers.add("Approved");
        ArrayList<String> camps = new ArrayList<>();
        ArrayList<String> students = new ArrayList<>();
        ArrayList<String> suggestions = new ArrayList<>();
        ArrayList<String> approveds = new ArrayList<>();
        for(Camp c:staff.getCampsCreated()){
            for(Suggestion sug :c.getSuggestions()){
                camps.add(c.getCampName());
                students.add(sug.getAuthor().getName());
                suggestions.add(sug.getCamp().toString());
                approveds.add(sug.getApproved()+"");
            }
        }
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camps);
        columns.add(students);
        columns.add(suggestions);
        columns.add(approveds);
        IViewPage p = new TablePromptOption("Camp Suggestions Committee members", headers, columns);
        p.perform();
    }
    public static void StaffAcceptSuggestion(Staff s){
        Staff staff = staffDataStore.retrieveData(new DataStoreRetrieve<Staff>(s)).get(0);

        IPromptPage<Suggestion> p = new AcceptRejectSuggestionPromptPage(staff);
        p.perform();
        campDataStore.manageData(staffDBService.DSSuggestionApprove(p.getObject(), studentDataStore, campDataStore));
    }

    public static void StaffGenerateStudentReport(Staff s){
        IPromptPage<ReportFilter> p = new GenerateStudentReportPromptPage();
        p.perform();
        String fileName = "ParticipantReport-" + UUID.randomUUID().toString();
        campDataStore.manageData(staffDBService.DSGenerateParticipantReport(fileName, p.getObject()));
        System.out.println(ANSI_GREEN_BACKGROUND+"Participant Report saved as " + fileName + ".csv"+ANSI_RESET);
    }

    public static void StaffGenerateEnquiryReport(){
        String fileName = "EnquiryReport-" + UUID.randomUUID().toString();
        campDataStore.manageData(staffDBService.DSGenerateEnquiryReport(fileName));
        System.out.println(ANSI_GREEN_BACKGROUND+"Enquiry Report saved as " + fileName + ".txt"+ANSI_RESET);
    }

    public static void StaffGeneratePerformanceReport(){
        String fileName = "PerformanceReport-" + UUID.randomUUID().toString();
        campDataStore.manageData(staffDBService.DSGeneratePerformanceReport(fileName,studentDataStore));
        System.out.println(ANSI_GREEN_BACKGROUND+"Performance Report saved as " + fileName + ".csv"+ANSI_RESET);
    }

    // student only
    public static void StudentGenerateStudentReport(Student s){
        IPromptPage<ReportFilter> p = new GenerateStudentReportPromptPage();
        p.perform();
        campDataStore.manageData(studentDBService.DSGenerateParticipantReport("ParticipantReport" + UUID.randomUUID().toString(), studentDataStore ,p.getObject()));
    }

    public static Page StudentDashBoard(Student s){
        Student student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(s)).get(0);
        IPromptPage<Page> dashboard = new StudentDashboardPromptPage(student);
        dashboard.perform();
		return dashboard.getObject();
    }
    private static void sortCamps(ArrayList<Camp> cs){
        IPrompt po= new PromptOption("How would you like to filter", true, "Camp name", sortyBy_str);
        po.startPrompt();
        SortBy sb = SortBy.fromString(po.getResult());
        if(sb.equals(SortBy.CampName)){
                cs.sort(
                    Comparator.comparing((Camp cc)->{
                        return cc.getCampName();
                    })
                );
        }else if (sb.equals(SortBy.ClosingDate)){
            cs.sort(
                    Comparator.comparing((Camp cc)->{
                        return cc.getClosingDate();
                    })
                );
        }else{
            cs.sort(
                    Comparator.comparing((Camp cc)->{
                        return cc.getLocation();
                    })
                );
        }
    }
    public static void ViewCampStudent(Student student){
        
        

        ArrayList<Camp> camps = campDataStore.retrieveData(studentDBService.DSCampRetrival());
        ArrayList<String> headers = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        headers.add("camp name");
        headers.add("closing date");
        headers.add("start date");
        headers.add("end date");
        headers.add("open to");
        headers.add("location");
        headers.add("remainding attendee slots");
        headers.add("remainding committee slots");
        headers.add("description");
        headers.add("visibility");
        
        ArrayList<String> camp_names = new ArrayList<>();
        ArrayList<String> closing_dates = new ArrayList<>();
        ArrayList<String> start_dates = new ArrayList<>();
        ArrayList<String> end_dates = new ArrayList<>();
        ArrayList<String> openTos = new ArrayList<>();
        ArrayList<String> locations = new ArrayList<>();
        ArrayList<String> remaindingASlots = new ArrayList<>();
        ArrayList<String> remaindingCSlots = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        ArrayList<String> visibilitys = new ArrayList<>();
        
        sortCamps(camps);
        for(Camp c:camps){
            camp_names.add(c.getCampName());
            closing_dates.add(sdf.format(c.getClosingDate()));
            start_dates.add(sdf.format(c.getDates()[0]));
            end_dates.add(sdf.format(c.getDates()[1]));
            openTos.add(c.getUserGroup().toString());
            locations.add(c.getLocation());
            remaindingASlots.add(c.getRemaindingSlots()+"");
            remaindingCSlots.add(c.getRemaindingCommitteeSlots()+"");
            descriptions.add(c.getDescription());
            visibilitys.add(c.getVisibility()+"");
        }
        
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camp_names);
        columns.add(closing_dates);
        columns.add(start_dates);
        columns.add(end_dates);
        columns.add(openTos);
        columns.add(locations);
        columns.add(remaindingASlots);
        columns.add(remaindingCSlots);
        columns.add(descriptions);
        columns.add(visibilitys);
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

    public static void StudentRegisterAsCommittee(Student s){
        ArrayList<Camp> availCamp = campDataStore.retrieveData(studentDBService.DSCampRetrival());
        IPromptPage<Camp> p = new RegisterForCampPromptPage(availCamp);
        p.perform();
        campDataStore.manageData(studentDBService.DSJoinCampAsCommittee(p.getObject(), studentDataStore));
    }

    public static void StudentSubmitEnquiry(Student s){
        Student student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(s)).get(0);
        IPromptPage<Enquiry> p = new CampEnquiryPromptPage(student);
        p.perform();
        campDataStore.manageData(studentDBService.DSEnquiryCreate(p.getObject(), studentDataStore));
    }

    public static void ViewEnquiryStudent(Student s){
        Student student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(s)).get(0);
        ArrayList<Enquiry> enquiry = student.getEnquireAbout();

        ArrayList<String> headers = new ArrayList<>();
        headers.add("Camp");
        headers.add("Enquiry");
        ArrayList<String> camp_names = new ArrayList<>();
        ArrayList<String> enquiry_text = new ArrayList<>();
        for(Enquiry e:enquiry){
            camp_names.add(e.getCamp().getCampName());
            enquiry_text.add(e.getText());
        }
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camp_names);
        columns.add(enquiry_text);
        IViewPage p = new TablePromptOption("Enquries made by you", headers,columns);
        p.perform();
    }


    public static void StudentEditEnquiry(Student s){
        Student student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(s)).get(0);
        ArrayList<Enquiry> enquiry = student.getEnquireAbout();
        IPromptPage<Enquiry> p = new EditEnquiryPromptPage(enquiry);
        p.perform();
        campDataStore.manageData(studentDBService.DSEnquiryEdit(p.getObject()));
    }
    private static void sortCampMembers(ArrayList<Pair<CampMember, String>> cs){
        IPrompt po= new PromptOption("How would you like to filter", true, "Camp name", sortyBy_str);
        po.startPrompt();
        SortBy sb = SortBy.fromString(po.getResult());
        if(sb.equals(SortBy.CampName)){
                cs.sort(
                    Comparator.comparing((Pair<CampMember, String> cc)->{
                        return cc.getFirst().getCamp().getCampName();
                    })
                );
        }else if (sb.equals(SortBy.ClosingDate)){
            cs.sort(
                    Comparator.comparing((Pair<CampMember, String> cc)->{
                        return cc.getFirst().getCamp().getClosingDate();
                    })
                );
        }else{
            cs.sort(
                    Comparator.comparing((Pair<CampMember, String> cc)->{
                        return cc.getFirst().getCamp().getLocation();
                    })
                );
        }
    }
    public static void ViewAllRegisteredCampsStudent(Student s){
        Student student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(s)).get(0);
        //need tot ask how would he like to Filter it by
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        ArrayList<String> headers = new ArrayList<>();
        headers.add("camp name");
        headers.add("description");
        headers.add("location");
        headers.add("closing date");
        headers.add("role");
        
        ArrayList<String> camp_names = new ArrayList<>();
        ArrayList<String> camp_details = new ArrayList<>();
        ArrayList<String> locations = new ArrayList<>();
        ArrayList<String> closingDates = new ArrayList<>();
        ArrayList<String> roles = new ArrayList<>();
        ArrayList<Pair<CampMember, String>> cms = new ArrayList<>();
        
        for(CampMember cm:student.getAttending()){
            cms.add(new Pair<>(cm, "Attendee"));
            
        }
        for(CampMember cm:student.getLeading()){
            cms.add(new Pair<>(cm, "Committee"));
        }
        sortCampMembers(cms);
        for(Pair<CampMember, String> ccc:cms){
            camp_names.add(ccc.getFirst().getCamp().getCampName());
            camp_details.add(ccc.getFirst().getCamp().getDescription());
            closingDates.add(formatter.format(ccc.getFirst().getCamp().getClosingDate()));
            locations.add(ccc.getFirst().getCamp().getLocation());
            roles.add(ccc.getSecond());    
        }
        
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camp_names);
        columns.add(camp_details);
        columns.add(locations);
        columns.add(closingDates);
        columns.add(roles);
        IViewPage p = new TablePromptOption("Camps attended by you", headers,columns);
        p.perform();
    }

    public static void ViewRepliesToEnquiryStudent(Student s){
        Student student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(s)).get(0);
        ArrayList<Enquiry> enquiries = student.getEnquireAbout();
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Camp");
        headers.add("Enquiry");
        headers.add("Reply");
        ArrayList<String> camp_names = new ArrayList<>();
        ArrayList<String> enquiresText = new ArrayList<>();
        ArrayList<String> repliesText = new ArrayList<>();
        for(Enquiry enquiry : enquiries){
            if (enquiry.getReplies().isEmpty()){
                camp_names.add(enquiry.getCamp().getCampName());
                enquiresText.add(enquiry.getText());
                repliesText.add("No reply :(");
                continue;
            }
            for (Message reply : enquiry.getReplies()) {
                camp_names.add(enquiry.getCamp().getCampName());
                enquiresText.add(enquiry.getText());
                repliesText.add(reply.getAuthor().getName() + ": " + reply.getText());
            }
        }

        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camp_names);
        columns.add(enquiresText);
        columns.add(repliesText);
        IViewPage p = new TablePromptOption("Replies to your enquries", headers,columns);
        p.perform();
    }

    // Committee
    public static void CommitteeMakeSuggestion(Student s){
        Student student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(s)).get(0);
        IPromptPage<Suggestion> p = new CreateSuggestionPromptPage(student);
        p.perform();
        campDataStore.manageData(studentDBService.DSSuggestionCreate(p.getObject(), studentDataStore));
    }
    public static void CommitteeEditSuggestion(Student s){
        Student student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(s)).get(0);
        IPromptPage<Suggestion> p = new EditSuggestionPromptPage(student);
        p.perform();
        campDataStore.manageData(studentDBService.DSSuggestionEdit(p.getObject()));
    }
    public static void DeleteSuggestion(Student s){
        Student student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(s)).get(0);
        IPromptPage<Suggestion> p = new DeleteSuggestionPromptPage(student);
        p.perform();
        campDataStore.manageData(studentDBService.DSSuggestioNDelete(p.getObject(), studentDataStore));
    }

    public static void ViewEnquiryCommittee(Student s){
        Student student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(s)).get(0);
        
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Camp");
        headers.add("Author");
        headers.add("Enquiry");
        ArrayList<String> camp_names = new ArrayList<>();
        ArrayList<String> author = new ArrayList<>();
        ArrayList<String> enquiry_text = new ArrayList<>();
        for(CampMember cm : student.getLeading()){
            for (Enquiry enquiry : cm.getCamp().getEnquiries()){
                camp_names.add(cm.getCamp().getCampName());
                author.add(enquiry.getAuthor().getName());
                enquiry_text.add(enquiry.getText());
            }
        }
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camp_names);
        columns.add(author);
        columns.add(enquiry_text);
        IViewPage p = new TablePromptOption("Enquries for camps you are leading", headers,columns);
        p.perform();
    }

    public static void ViewSuggestionCommittee(Student s){
        Student student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(s)).get(0);
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Camp");
        headers.add("Suggestion");
        headers.add("Approved");
        ArrayList<String> camps = new ArrayList<>();
        ArrayList<String> suggestions = new ArrayList<>();
        ArrayList<String> approveds = new ArrayList<>();
        for(Suggestion suggestion : student.getSuggestions()){
            for (CampMember cm: student.getLeading()){
                if (cm.getCamp().isEquals(suggestion.getCamp())){
                    camps.add(cm.getCamp().toString());
                    break;
                }
            }
            suggestions.add(suggestion.getCamp().toString());
            approveds.add(suggestion.getApproved() + "");
        }
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camps);
        columns.add(suggestions);
        columns.add(approveds);
        IViewPage p = new TablePromptOption("Camp Suggestions by you", headers, columns);
        p.perform();
    }

    public static void CommitteeReplyEnquiry(Student s){
        Student student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(s)).get(0);
        ArrayList<Camp> camps = new ArrayList<>();
        for (CampMember cm : student.getLeading()) {
            camps.add(cm.getCamp());
        }
        IPromptPage<Pair<Enquiry,Message>> p = new ReplyToEnquiryPromptPage(student, camps);
        p.perform();
        campDataStore.manageData(studentDBService.DSEnquiryReply(p.getObject()));
    }

    // Controller

    public static void init(){
        for(SortBy rf : sortBy){
            sortyBy_str.add(rf.toString());
        }
        staffDataStore = new DataStore<Staff>();
		studentDataStore = new DataStore<Student>();
		campDataStore = new DataStore<Camp>();
		// Populate userDataStore with Staff and Student objects.
		staffDataStore.manageData(new UserDataStoreLoad<Staff>(new StaffCSVLoader("./src/.data/staff.csv")));
		studentDataStore.manageData(new UserDataStoreLoad<Student>(new StudentCSVLoader("./src/.data/student.csv")));
        
        try {
            DELETETHIS_campinit();
        } catch (ParseException e) {
            System.out.println(e);
        }
        
    }

    private static Date DATE(String dateStr) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        return formatter.parse(dateStr);
    }

    private static void DELETETHIS_campinit() throws ParseException{

        Student student;
        Staff staff;
        Camp camp;
        Camp tempCamp_NTUvisible;
        Camp tempCamp2;
        StudentDBService studentDBService;
        StaffDBService staffDBService;

        // Log in as STF
        staff = staffDataStore.retrieveData(new UserLoginRetrival<Staff>("STF", "password")).get(0);
        staffDBService = new StaffDBService(staff);

        // STF creates NTU Camp - Invisible
        camp = new Camp();
        camp.setCreatedBy(staff);
        camp.setCampName("NTU Camp - Invisible");
        camp.setDates(new Date[]{DATE("27112023"),DATE("01122023")});
        camp.setClosingDate(DATE("26112000"));
        camp.setTotalSlots(15);
        camp.setCommitteeSlot(10);
        camp.setUserGroup(GroupName.NTU);
        camp.setLocation("NTU Campus");
        camp.setDescription("Camp for NTU");
        camp.setVisibility(false);	
        campDataStore.manageData(staffDBService.DSCreateCamp(camp, staffDataStore));
        
        // STF creates NTU Camp - Visible
        camp = new Camp();
        camp.setCreatedBy(staff);
        camp.setCampName("NTU Camp - Visible");
        camp.setDates(new Date[]{DATE("27112023"),DATE("01122023")});
        camp.setClosingDate(DATE("26112023"));
        camp.setTotalSlots(15);
        camp.setCommitteeSlot(10);
        camp.setUserGroup(GroupName.NTU);
        camp.setLocation("NTU Campus");
        camp.setDescription("Camp for NTU");
        camp.setVisibility(true);	
        campDataStore.manageData(staffDBService.DSCreateCamp(camp, staffDataStore));

        tempCamp_NTUvisible = camp.copyOf();

        // SCSE Camp
        camp = new Camp();
        camp.setCreatedBy(staff);
        camp.setCampName("SCSE Camp - Visible");
        camp.setDates(new Date[]{DATE("30112024"),DATE("07122024")});
        camp.setClosingDate(DATE("26112024"));
        camp.setTotalSlots(15);
        camp.setCommitteeSlot(10);
        camp.setUserGroup(GroupName.SCSE);
        camp.setLocation("SWLab2");
        camp.setDescription("Camp for SCSE");
        camp.setVisibility(true);	
        campDataStore.manageData(staffDBService.DSCreateCamp(camp, staffDataStore));
        tempCamp2 = camp.copyOf();

        // Login as STF2
        staff = staffDataStore.retrieveData(new UserLoginRetrival<Staff>("STF2", "password")).get(0);
        staffDBService = new StaffDBService(staff);

        // STF2 Create NBS CAmp
        camp = new Camp();
        camp.setCreatedBy(staff);
        camp.setCampName("NBS Camp - Visible");
        camp.setDates(new Date[]{DATE("30112023"),DATE("07122023")});
        camp.setClosingDate(DATE("26112023"));
        camp.setTotalSlots(15);
        camp.setCommitteeSlot(10);
        camp.setUserGroup(GroupName.NBS);
        camp.setLocation("ur mother");
        camp.setDescription("Camp for NBS");
        camp.setVisibility(true);	
        campDataStore.manageData(staffDBService.DSCreateCamp(camp, staffDataStore));

        // Login as STD
        student = studentDataStore.retrieveData(new UserLoginRetrival<Student>("STD", "password")).get(0);
        studentDBService = new StudentDBService(student);
        
        // STD join NTU Camp - Visible
        campDataStore.manageData(studentDBService.DSJoinCampAsAttendee(tempCamp_NTUvisible, studentDataStore));
        // STD join SCSE Camp - VIsible as committee
        campDataStore.manageData(studentDBService.DSJoinCampAsCommittee(tempCamp2, studentDataStore));

        // STD make enquiry for NTU Camp - Visible
        Enquiry enquiry = new Enquiry("This one what?", student, tempCamp_NTUvisible);
        campDataStore.manageData(studentDBService.DSEnquiryCreate(enquiry, studentDataStore));
        Enquiry tempEnq = enquiry.copyOf();

        // STD Make suggestion for SCSE Camp - Visible
        Suggestion suggestion = new Suggestion(student,tempCamp2.copyOf());
        suggestion.getCamp().setDescription("i set in tps htsotiah pao ha ph ap hap p");
        campDataStore.manageData(studentDBService.DSSuggestionCreate(suggestion,studentDataStore));

        // Staff Reply to STD's enquiry
        staff = staffDataStore.retrieveData(new UserLoginRetrival<Staff>("STF", "password")).get(0);
        staffDBService = new StaffDBService(staff);
        Message reply = new Message("This one ur mother", staff);
        campDataStore.manageData(staffDBService.DSEnquiryReply(new Pair<Enquiry,Message>(tempEnq,reply)));

        // Login as STD2
        student = studentDataStore.retrieveData(new UserLoginRetrival<Student>("STD2", "password")).get(0);
        studentDBService = new StudentDBService(student);

        // STD2 joim ntucamp visible
        campDataStore.manageData(studentDBService.DSJoinCampAsCommittee(tempCamp_NTUvisible, studentDataStore));
        campDataStore.manageData(studentDBService.DSJoinCampAsAttendee(tempCamp2, studentDataStore));
    }
}
