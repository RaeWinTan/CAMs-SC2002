package com.example;


import java.util.HashMap;

import com.example.dataloader.StaffCSVLoader;
import com.example.dataloader.StudentCSVLoader;
import com.example.datastore.DataStore;
import com.example.datastore.operator.UserDataStoreLoad;
import com.example.datastore.operator.UserLoginRetrival;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;

import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.InvalidLoginCredentialException;
import com.example.view.IPromptPage;
import com.example.view.LoginPromptPage;

enum UserType{
	STAFF,
	STUDENT
}

public class App {

	public static DataStore<Staff> staffDataStore;
	public static DataStore<Student> studentDataStore;
	public static DataStore<Camp> campDataStore;
	public static DataStore<Enquiry> enquiryDataStore;
	public static DataStore<Suggestion> suggestionDataStore;

	
	public static Staff staff;
	public static Student student;
	public static UserType userType;
	

	public static void main(String arg[]) {
		initialise();
		workFlow();
	}
	public static void login(){
		String username = "";
		String password = "";
		String userType = "";
		while (staff==null && student==null) {
			IPromptPage loginPage = redirect("login");
			loginPage.prompting();
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
		login();
		
	}

	public static IPromptPage redirect(String pagename){
		//check for those page name that has an array set for them, got to create here
		IPromptPage rtn;
		if(pagename=="login"){
			return new LoginPromptPage();
		}
		else if(pagename == "AcceptRejectSuggestionPromptPage"){
			return new AcceptRejectSuggestionPromptPage();
		}
		else if(pagename == "withdrawFromCamp"){
			return new CampWithdrawalPromptPage();
		}
		else if(pagename == "changePassword"){
			return new ChangePasswordPromptPage();
		}
		else if(pagename == "CreateCampPromptPage"){
			return new CreateCampPromptPage();
		}
		else if(pagename == "EditCampPromptPage"){
			return new EditCampPromptPage();
		}
		else if(pagename == "registerCommmitteeCamp"){
			return new RegisterForCampCommitteeMember();
		}
		else if(pagename == "editSuggestion"){
			return new editSuggestion();
		}
		else if(pagename == "deleteSuggestion"){
			return new deleteSuggestion;
		}
		else if(pagename == "registerAttendeeCamp"){
			return new RegisterForCampPromptPage();
		}
		else if (pagename == "ReplyToEnquiryPromptPage"){
			return new ReplyToEnquiryPromptPage();
		}
		else if (pagename == "enquireCamp"){
			return new SubmitCampPromptEnquiry();
		}
		else if (pagename == "suggestionCamp"){
			return new SubmitCampSuggestionPromptPage();
		}
		else if (pagename == "ViewCampsStaff"){
			return new ViewCampsStaff();
		}
		else if(pagename == "viewAllRegisteredCamps"){
			return new viewAllRegisteredCamps();
		}
		else if (pagename == "viewAvailableCamps"){
			return new ViewCampsStudent();
		}
		else if (pagename == "ViewCommitteePerformance"){
			return new ViewCommitteePerformance();
		}
		else if (pagename == "ViewCreatedCamps"){
			return new ViewCreatedCamps();
		}
		else if(Pagename == "viewRepliesToEnquiry"){
			return new viewRepliesToEnquiry();
		}
		else if (pagename == "viewEnquiriesFromStudent"){
			return new ViewEnquiryReport();
		}
		else if (pagename == "ViewListOfCommittee"){
			return new ViewListOfCommittee();
		}
		else if (pagename == "generateReportAtteedeesForEachCamp"){
			return new ViewListOfStudent();
		}
		else if (pagename == "viewSubmittedSuggestions"){
			return new ViewOwnSuggestions();
		}
		else if (pagename ==  "ViewSuggestion"){
			return new ViewSuggestion();
		}
		else {
		return new LoginPromptPage();}
	} 


	private static void initialise(){
		// Initialise Datastores.
		staffDataStore = new DataStore<Staff>();
		studentDataStore = new DataStore<Student>();
		campDataStore = new DataStore<Camp>();
		enquiryDataStore = new DataStore<Enquiry>();
		suggestionDataStore = new DataStore<Suggestion>();
		
		// Populate userDataStore with Staff and Student objects.
		staffDataStore.manageData(new UserDataStoreLoad<Staff>(new StaffCSVLoader("./src/.data/staff.csv")));
		studentDataStore.manageData(new UserDataStoreLoad<Student>(new StudentCSVLoader("./src/.data/student.csv")));

		
		
	}
}

