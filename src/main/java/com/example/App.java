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
import com.example.exception.InvalidLoginCredentialException;

import com.example.view.*;



public class App {


	

	public static void main(String arg[]) {
		PageGenerator.init();
		workFlow();
	}

	public static void staffRedirection(Page currentPage, Staff staff){
		if(currentPage==Page.CreateCamp){
			PageGenerator.StaffCreateCamp(staff);
		}else if (currentPage==Page.ViewCampsStaff){
			PageGenerator.ViewCampsStaff();
		}
	}
	public static void studentRedirection(Page currentPage, Student student){

	}

	public static void workFlow(){
		Page currentPage = Page.Login;
		while(currentPage.equals(Page.Login)){
			Staff staff = null;
			Student student = null;
			User u = PageGenerator.Login();
			if(u instanceof Staff){
				staff = (Staff) u;
				currentPage = Page.StaffDashBoard;
			} else{
				student = (Student) u;
				currentPage = Page.StudentDashBoard;
			}
			while(!currentPage.equals(Page.Login)){//while the person is logged in
				if(currentPage.equals(Page.StaffDashBoard)){
					currentPage = PageGenerator.StaffDashBoard();
				}else if (currentPage.equals(Page.StudentDashBoard)){
					currentPage = PageGenerator.StudentDashBoard(student);
				}else{
					if(currentPage.equals(Page.Logout)){
						currentPage = Page.Login;
					}else{
						if(u instanceof Staff){
							staffRedirection(currentPage, staff);
							currentPage = Page.StaffDashBoard;
						}else{
							studentRedirection(currentPage, student);
							currentPage = Page.StudentDashBoard;
						}
						
					}
					
				}
				
			}
		}
	}
	
}

