package com.example;
import com.example.controllerlibs.Page;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.datastructure.User;
import com.example.exception.InvalidLoginCredentialException;
public class App {
	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
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

