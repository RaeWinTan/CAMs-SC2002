package com.example;


import java.util.HashMap;

import com.example.dataloader.StaffCSVLoader;
import com.example.dataloader.StudentCSVLoader;
import com.example.datastore.bilist.BiListDataStore;
import com.example.datastore.monolist.MonoListDataStore;
import com.example.datastore.monolist.operator.UserDataStoreLoad;
import com.example.datastore.monolist.operator.UserLoginRetrival;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;

import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.InvalidLoginCredentialException;
import com.example.utility.DataStorePair;
import com.example.view.IPromptPage;
import com.example.view.LoginPromptPage;

public class App {

	public static MonoListDataStore<Staff> staffDataStore;
	public static MonoListDataStore<Student> studentDataStore;
	public static MonoListDataStore<Camp> campDataStore;
	public static MonoListDataStore<Enquiry> enquiryDataStore;
	public static MonoListDataStore<Suggestion> suggestionDataStore;

	public static BiListDataStore<DataStorePair<Student,Camp>> scDatastore;
	public static HashMap<String, IPromptPage> hm;

	public static void main(String arg[]) {
		initialise();
		workFlow();
		

	}
	public static void workFlow(){
		
		Staff staff = null;
		Student student = null;
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
				System.out.println(e.toString());

			}
		}

		System.out.println("User type: " + userType);
		System.out.println("Username: " + username);
		System.out.println("password: " + password);
		if (userType.equals("Staff")){
			System.out.println("Welcome, " + staff.getName());
		} else {
			System.out.println("Welcome, " + student.getName());
		}

	}

	public static IPromptPage redirect(String pagename){
		return hm.get(pagename);
	} 


	private static void initialise(){
		// Initialise Datastores.
		staffDataStore = new MonoListDataStore<Staff>();
		studentDataStore = new MonoListDataStore<Student>();
		campDataStore = new MonoListDataStore<Camp>();
		enquiryDataStore = new MonoListDataStore<Enquiry>();
		suggestionDataStore = new MonoListDataStore<Suggestion>();

		scDatastore = new BiListDataStore<DataStorePair<Student,Camp>>();
		
		// Populate userDataStore with Staff and Student objects.
		staffDataStore.manageData(new UserDataStoreLoad<Staff>(new StaffCSVLoader("./src/.data/staff.csv")));
		studentDataStore.manageData(new UserDataStoreLoad<Student>(new StudentCSVLoader("./src/.data/student.csv")));

		hm = new HashMap<>();
		hm.put("login", new LoginPromptPage());
		
	}
}

