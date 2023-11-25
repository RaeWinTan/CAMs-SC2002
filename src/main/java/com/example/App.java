package com.example;
import com.example.controllerlibs.Page;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.datastructure.User;
import com.example.exception.InvalidLoginCredentialException;
public class App {
	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
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
			System.out.println(ANSI_GREEN_BACKGROUND+currentPage.getPageView()+ANSI_RESET);
			Staff staff = null;
			Student student = null;
			User u;
			try{
				u = PageGenerator.Login();
				if(u instanceof Staff){
					staff = (Staff) u;
					currentPage = Page.StaffDashBoard;
				} else{
					student = (Student) u;
					currentPage = Page.StudentDashBoard;
				}
			}catch(InvalidLoginCredentialException e){
				System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
				continue;
			}
			while(!currentPage.equals(Page.Login)){//while the person is logged in
				System.out.println(ANSI_GREEN_BACKGROUND+currentPage.getPageView()+ANSI_RESET);
				try{
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
				} catch (Exception e){
					System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
					if(u instanceof Staff) currentPage = Page.StaffDashBoard;
					else currentPage = Page.StudentDashBoard;
				}
			}
		}
	}
	
}

