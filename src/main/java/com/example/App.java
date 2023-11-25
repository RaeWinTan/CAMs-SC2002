package com.example;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.example.dataloader.StaffCSVLoader;
import com.example.dataloader.StudentCSVLoader;
import com.example.dataservice.admin.StaffDBService;
import com.example.dataservice.student.StudentDBService;
import com.example.datastore.DataStore;
import com.example.datastore.operator.StaffCampCreate;
import com.example.datastore.operator.StaffCampRetrival;
import com.example.datastore.operator.UserDataStoreLoad;
import com.example.datastore.operator.UserLoginRetrival;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.GroupName;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.InvalidLoginCredentialException;
import com.example.exception.ObjectClash;
import com.example.view.*;



public class App {

	public static DataStore<Staff> staffDataStore;
	public static DataStore<Student> studentDataStore;
	public static DataStore<Camp> campDataStore;
	
	public static StaffDBService staffDBService;
	public static StudentDBService studentDBService;

	

	public static void main(String arg[]) {
		initialise();
		workFlow();
		
	}

	public static void workFlow(){
		Staff staff = null;
		Student student = null;
		while(staff==null && student == null){
			IPromptPage<UserCredentials> loginPage = new LoginPromptPage();
			loginPage.perform();
			UserCredentials uc = loginPage.getObject();
			try{
				if(uc.getUserType()==UserType.STAFF){
					staff = staffDataStore.retrieveData(new UserLoginRetrival<Staff>(uc.getUserId(), uc.getPassword())).get(0);
					staffDBService = new StaffDBService(staff);
				}else{
					student = studentDataStore.retrieveData(new UserLoginRetrival<Student>(uc.getUserId(), uc.getPassword())).get(0);
					studentDBService = new StudentDBService(student);
				}
			} catch (InvalidLoginCredentialException e){
				System.out.println(e.getMessage());
				continue;
			}
			while(staff!=null || student!=null){
				Page currentPage;
				IPromptPage<Page> dashboard;
				if(staff!=null){
					dashboard = new StaffDashboardPromptPage();
					currentPage = Page.StaffDashBoard;
				}
				else{
					dashboard = new StudentDashboardPromptPage(student);
					currentPage = Page.StudentDashBoard;
				}
				dashboard.perform();
				currentPage = dashboard.getObject();
				
					if(currentPage==Page.CreateCamp){
						IPromptPage<Camp> p = new CreateCampPromptPage();
						p.perform();
						Camp newCamp = p.getObject();
						newCamp.setCreatedBy(staff);
						campDataStore.manageData(staffDBService.DSCreateCamp(p.getObject(), staffDataStore));
					}else if (currentPage==Page.ViewCampsStaff){
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
				
				
			}
		}
	}
	

	private static void initialise(){
		// Initialise Datastores.
		staffDataStore = new DataStore<Staff>();
		studentDataStore = new DataStore<Student>();
		campDataStore = new DataStore<Camp>();
		// Populate userDataStore with Staff and Student objects.
		staffDataStore.manageData(new UserDataStoreLoad<Staff>(new StaffCSVLoader("./src/.data/staff.csv")));
		studentDataStore.manageData(new UserDataStoreLoad<Student>(new StudentCSVLoader("./src/.data/student.csv")));

		
		
	}
}

