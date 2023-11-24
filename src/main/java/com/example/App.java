package com.example;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.example.dataloader.StaffCSVLoader;
import com.example.dataloader.StudentCSVLoader;
import com.example.dataservice.admin.StaffDBService;
import com.example.dataservice.student.StudentDBService;
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
	
	public static Staff staff;
	public static Student student;
	public static UserType userType;
	public static String currentPage;

	public static StaffDBService staffDBService;
	public static StudentDBService studentDBService;
	

	public static void main(String arg[]) {
		initialise();
		
		System.out.println("\n====Camp Create test====\n");
		campCreateTest();

		System.out.println("\n====Staff retrieve all Camp test====\n");
		staffRetrieveAllCampTest();

		System.out.println("\n====Staff retrieve relevant Camps test====\n");
		staffRetrieveCampTest();
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

	private static void loginStaff(String id, String pw){
		staff = staffDataStore.retrieveData(new UserLoginRetrival<Staff>(id, pw)).get(0);
		staffDBService = new StaffDBService(staff);
	}

	private static void loginStudent(String id, String pw){
		student = studentDataStore.retrieveData(new UserLoginRetrival<Student>(id, pw)).get(0);
		studentDBService = new StudentDBService(student);
	}

	private static void campCreateTest(){
		Camp camp;
		Date[] dates = new Date[] {new Date(),new Date()};

		loginStaff("UPAM", "password");

		camp = new Camp();
		camp.setCreatedBy(staff);
		camp.setCampName("NTU Camp");
		camp.setDates(dates);
		camp.setClosingDate(new Date());
		camp.setTotalSlots(15);
		camp.setCommitteeSlot(10);
		camp.setUserGroup(GroupName.NTU);
		camp.setLocation("NTU");
		camp.setDescription("Camp for NTU");
		camp.setVisibility(false);	
		campDataStore.manageData(staffDBService.DSCreateCamp(camp, staffDataStore));

		camp = new Camp();
		camp.setCreatedBy(staff);
		camp.setCampName("SCSE Camp");
		camp.setDates(dates);
		camp.setClosingDate(new Date());
		camp.setTotalSlots(10);
		camp.setCommitteeSlot(5);
		camp.setUserGroup(GroupName.SCSE);
		camp.setLocation("SCSE");
		camp.setDescription("Camp for SCSE");
		camp.setVisibility(false);	
		campDataStore.manageData(staffDBService.DSCreateCamp(camp, staffDataStore));

		loginStaff("ANWIT", "password");

		camp = new Camp();
		camp.setCreatedBy(staff);
		camp.setCampName("SSS Camp");
		camp.setDates(dates);
		camp.setClosingDate(new Date());
		camp.setTotalSlots(20);
		camp.setCommitteeSlot(5);
		camp.setUserGroup(GroupName.SSS);
		camp.setLocation("SSS");
		camp.setDescription("Camp for SSS");
		camp.setVisibility(false);	
		campDataStore.manageData(staffDBService.DSCreateCamp(camp, staffDataStore));
	}

	private static void staffRetrieveAllCampTest(){
		for (Camp camp : campDataStore.retrieveData(staffDBService.DSCampRetrival())) {
			System.out.println(camp.toString());
		}
	}

	private static void staffRetrieveCampTest(){
		loginStaff("ANWIT", "password");
		System.out.println("\n\nCamp by ANWIT");
		for (Camp camp : campDataStore.retrieveData(staffDBService.DSRelevantCampRetrival())) {
			System.out.println(camp.toString());
		}
		

		loginStaff("UPAM", "password");
		System.out.println("\n\nCamp by UPAM");
		for (Camp camp : campDataStore.retrieveData(staffDBService.DSRelevantCampRetrival())) {
			System.out.println(camp.toString());
		}
	}
}