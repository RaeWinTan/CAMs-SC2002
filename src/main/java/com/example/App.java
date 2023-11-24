package com.example;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.example.dataloader.StaffCSVLoader;
import com.example.dataloader.StudentCSVLoader;
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
	public static DataStore<Enquiry> enquiryDataStore;
	public static DataStore<Suggestion> suggestionDataStore;

	
	public static Staff staff;
	public static Student student;
	public static UserType userType;
	public static String currentPage;

	public static boolean isCommittee;
	

	public static void main(String arg[]) {
		initialise();
		/*IPromptPage<UserCredentials> loginPage = new LoginPromptPage();
		loginPage.perform();
		UserCredentials uc = loginPage.getObject();*/
		Staff staff = staffDataStore.retrieveData(new UserLoginRetrival<Staff>("OURIN", "password")).get(0);
	
		//IPromptPage<Camp> createCamp = new CreateCampPromptPage();
		//createCamp.perform();
		//System.out.println(createCamp.getObject().toString());
		Camp newCamp = new Camp();
		Date[] ds = new Date[2];
		ds[0] = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			sdf.setLenient(false);
			ds[1] = sdf.parse("21/01/2023");
		}catch(Exception e){

		}

		newCamp.setCampName("Camp 1");
        newCamp.setDates(ds);
        newCamp.setClosingDate(new Date());
        newCamp.setTotalSlots(1);
        newCamp.setCommitteeSlot(2);
        newCamp.setLocation("as;ldfjkasd");
        newCamp.setDescription("a;lsdjkfa;lfd");
        newCamp.setUserGroup(GroupName.ADM);
        newCamp.setVisibility(true);
		newCamp.setCreatedBy(staff);
		campDataStore.manageData(new StaffCampCreate(staff, newCamp, staffDataStore));
		newCamp = new Camp();
		newCamp.setCampName("Camp 1");
        newCamp.setDates(ds);
        newCamp.setClosingDate(new Date());
        newCamp.setTotalSlots(10);
        newCamp.setCommitteeSlot(2);
        newCamp.setLocation("as;ldfjkasd");
        newCamp.setDescription("a;lsdjkfa;lfd");
        newCamp.setUserGroup(GroupName.ADM);
        newCamp.setVisibility(true);
		newCamp.setCreatedBy(staff);
		try{
			campDataStore.manageData(new StaffCampCreate(staff, newCamp, staffDataStore));		
		}catch(ObjectClash e){
			System.out.println(e.getMessage());
		}
		for(Camp c: campDataStore.retrieveData(new StaffCampRetrival())){
			System.out.println(c.toString());
		}
	}
	/* 
	public static void login(){
		String username = "";
		String password = "";
		String userType = "";
		while (staff==null && student==null) {
			IPromptPage loginPage = redirect("login");
			loginPage.perform();
			for(int i = 0; i < loginPage.returnInputs().size();i++){
				if(loginPage.returnInputs().get(i).getResult().getFirst() == "username"){
					username = loginPage.returnInputs().get(i).getResult().getSecond();
				}
				if(loginPage.returnInputs().get(i).getResult().getFirst() == "password"){
					password = loginPage.returnInputs().get(i).getResult().getSecond();
				}
				if(loginPage.returnInputs().get(i).getResult().getFirst() == "userType"){
					userType = loginPage.returnInputs().get(i).getResult().getSecond();
				}
			}
			try {
				if (userType.equals("Staff")){
					staff = staffDataStore.retrieveData(new UserLoginRetrival<Staff>(username, password)).get(0);
				} else{
					student = studentDataStore.retrieveData(new UserLoginRetrival<Student>(username, password)).get(0);			
				}
			}
			catch (InvalidLoginCredentialException e){
				System.out.println(e.getMessage());
			}
		}
	}
	public static void workFlow(){
		while(true){
			login();
			while(staff!=null||student!=null){//while it is logged in
				IViewPage dashboard;
				if(userType==UserType.STAFF){
					dashboard = redirect("staffDashboard");
				}else{
					dashboard = redirect("studentDashboard");
				}
				dashboard.perform();
				currentPage = ((IPromptPage) dashboard).returnInputs().get(0).getResult().getSecond();
				if(currentPage.equals("logout")) break;
				IViewPage newPage = redirect(currentPage);
				if(newPage instanceof IPromptPage){
						((IPromptPage)newPage).returnInputs();
						//here must handel that 
				}else{ //it is a display page

				}
			}
			System.out.println("SUCCESSFUL LOGOUT");
			staff = null;
			student = null;
			
		}
		

		
	}

	public static IViewPage redirect(String pagename){
		
	} 
	*/

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

