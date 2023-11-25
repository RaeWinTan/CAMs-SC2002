package com.example;

import java.util.ArrayList;

import com.example.controllerlibs.Page;
import com.example.controllerlibs.UserCredentials;
import com.example.controllerlibs.UserType;
import com.example.dataloader.StaffCSVLoader;
import com.example.dataloader.StudentCSVLoader;
import com.example.dataservice.admin.StaffDBService;
import com.example.dataservice.student.StudentDBService;
import com.example.datastore.DataStore;
import com.example.datastore.operator.UserDataStoreLoad;
import com.example.datastore.operator.UserLoginRetrival;
import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.datastructure.User;
import com.example.view.IPromptPage;
import com.example.view.IViewPage;
import com.example.view.TablePromptOption;
import com.example.view.pages.CreateCampPromptPage;
import com.example.view.pages.LoginPromptPage;
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
    public static Page StudentDashBoard(Student student){
        IPromptPage<Page> dashboard = new StudentDashboardPromptPage(student);
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
    public static void init(){
        staffDataStore = new DataStore<Staff>();
		studentDataStore = new DataStore<Student>();
		campDataStore = new DataStore<Camp>();
		// Populate userDataStore with Staff and Student objects.
		staffDataStore.manageData(new UserDataStoreLoad<Staff>(new StaffCSVLoader("./src/.data/staff.csv")));
		studentDataStore.manageData(new UserDataStoreLoad<Student>(new StudentCSVLoader("./src/.data/student.csv")));
    }
}
