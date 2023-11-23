package com.example;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.example.dataloader.StaffCSVLoader;
import com.example.dataloader.StudentCSVLoader;
import com.example.datastore.DataStore;
import com.example.datastore.operator.UserDataStoreLoad;
import com.example.datastore.operator.UserLoginRetrival;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.GroupName;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.InvalidLoginCredentialException;




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
		Staff testStaff = loginStaff("UPAM", "password");
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

	private static Staff loginStaff(String id, String pw){
		return staffDataStore.retrieveData(new UserLoginRetrival<Staff>(id, pw)).get(0);
	}

	private static Student loginStudent(String id, String pw){
		return studentDataStore.retrieveData(new UserLoginRetrival<Student>(id, pw)).get(0);
	}

	private static void campCreateTest(Staff staff){
		Camp camp;
		Date[] dates = {};
		dates[0] = new Date();
		dates[0] = new Date();

		camp = new Camp();
		camp.setCreatedBy(staff);
		camp.setCampName("NTU Camp");
		camp.setDates(dates);
		camp.setClosingDate(new Date());
		camp.setTotalSlots(15);
		camp.setCommitteeSlot(10);
		camp.setUserGroup(GroupName.NTU);
		camp.setDescription("Ur mother's camp");
	}
}

