package com.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;  


import java.util.ArrayList;
import java.util.Date;

import com.example.DataLoaderPackage.StaffCSVLoader;
import com.example.DataLoaderPackage.StudentCSVLoader;
import com.example.DataStoreOperatorPackage.UserDataStoreLoad;
import com.example.DataStoreOperatorPackage.RetrivalOperator.UserLoginRetrival;
import com.example.DataStructurePackage.Camp;
import com.example.DataStructurePackage.Enquiry;
import com.example.DataStructurePackage.GroupName;
import com.example.DataStructurePackage.Suggestion;
import com.example.DataStructurePackage.User;
import com.example.UtilityPackage.DataStorePair;
import com.example.datastore.bilist.BiListDataStore;
import com.example.datastore.monolist.MonoListDataStore;
import com.example.DataStructurePackage.Staff;
import com.example.DataStructurePackage.Student;

public class App {

	public static MonoListDataStore<Staff> staffDataStore;
	public static MonoListDataStore<Student> studentDataStore;
	public static MonoListDataStore<Camp> campDataStore;
	public static MonoListDataStore<Enquiry> enquiryDataStore;
	public static MonoListDataStore<Suggestion> suggestionDataStore;

	public static BiListDataStore<DataStorePair<Student,Camp>> scDatastore;
	public static void main(String arg[]) {

		initialise();


		// hardcoded staff
		User staffUser = staffDataStore.retrieveData(new UserLoginRetrival<Staff>("HUKUMAR","password")).get(0);

		// hardcoded student
		User studentUser = studentDataStore.retrieveData(new UserLoginRetrival<Student>("KOH1", "password")).get(0);
	


		Staff staff = (Staff) staffUser;
		Student student = (Student) studentUser;

		CampTest(staff, student);
		UserTest();

       
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
		
		// enquriy.addReply(reply);
		// enquiryDataStore.manageData(staff.getDbService().GetEnquiryDBService().DSAddReplyEnquiry(enquiry));

		// // INSIDE DATASTORE
		// find enquiry
		// realenquiry.reply = obj.reply;
	}

	private static void CampTest(Staff staff, Student student){
 		Camp c;

		c = new Camp("ADM invisible", new ArrayList<Date>(), new Date(), GroupName.ADM, "ss",10,5,"dd",false,staff);
        campDataStore.manageData(staff.getDbService().DSCreateCamp(c));

        c = new Camp("ADM visible", new ArrayList<Date>(), new Date(), GroupName.ADM, "ss",10,5,"dd",true,staff);
        campDataStore.manageData(staff.getDbService().DSCreateCamp(c));

		c = new Camp("SCSE invisible", new ArrayList<Date>(), new Date(), GroupName.SCSE, "ss",10,5,"dd",false,staff);
        campDataStore.manageData(staff.getDbService().DSCreateCamp(c));

		c = new Camp("SCSE visible", new ArrayList<Date>(), new Date(), GroupName.SCSE, "ss",10,5,"dd",true,staff);
        campDataStore.manageData(staff.getDbService().DSCreateCamp(c));


        System.out.println("Staff retrieve");
		ArrayList<Camp> allCampDataRetrivedByStaff;
		
		allCampDataRetrivedByStaff = campDataStore.retrieveData(staff.getDbService().DSCampRetrival());
        allCampDataRetrivedByStaff.forEach(camp->{
            System.out.println("\t" + camp.getCampName());
        });

        System.out.println("Student retrieve");
        campDataStore.retrieveData(student.getDbService().DSCampRetrival(scDatastore)).forEach(camp->{
            System.out.println("\t" + camp.getCampName());
        });
		
		System.out.println("Changing first camp name");
		Camp editedCamp = allCampDataRetrivedByStaff.get(0);
		editedCamp.setCampName("Renamed camp");

		System.out.println("Staff retrieve again (before applying changes)");
		campDataStore.retrieveData(staff.getDbService().DSCampRetrival()).forEach(camp->{
            System.out.println("\t" + camp.getCampName());
        });

		System.out.println("Applying changes");
		campDataStore.manageData(staff.getDbService().DSEditCamp(editedCamp));

		System.out.println("Staff retrieve again (after applying changes)");
		allCampDataRetrivedByStaff = campDataStore.retrieveData(staff.getDbService().DSCampRetrival());

		allCampDataRetrivedByStaff.forEach(camp->{
            System.out.println("\t" + camp.getCampName());
        });

		System.out.println("Delete second camp");
		Camp campToDelete = allCampDataRetrivedByStaff.get(1);
		campDataStore.manageData(staff.getDbService().DSDeleteCamp(campToDelete));

		campDataStore.retrieveData(staff.getDbService().DSCampRetrival()).forEach(camp->{
			System.out.println("\t" + camp.getCampName());
		});
	}

	private static void UserTest(){
		Staff staff = staffDataStore.retrieveData(new UserLoginRetrival<Staff>("HUKUMAR","password")).get(0);

		System.out.println("Current user password: " + staff.getPassword());
		System.out.println("Changing user password");
		staff.setPassword("newpassword");

		User staff2;
		staff2 = staffDataStore.retrieveData(new UserLoginRetrival<Staff>("HUKUMAR","password")).get(0);
		System.out.println("User password (before applying changes): " + staff2.getPassword());
		
		staffDataStore.manageData(staff.getDbService().DBEditUser(staff));
		staff2 = staffDataStore.retrieveData(new UserLoginRetrival<Staff>("HUKUMAR","newpassword")).get(0);
		
		System.out.println("User password (after applying changes): " + staff.getPassword());
	}
}

