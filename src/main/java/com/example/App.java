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
		}else if(currentPage==Page.EditCamp){
			PageGenerator.StaffEditCamp(staff);
		}else if(currentPage==Page.ViewEnquiries){
			PageGenerator.ViewEnquiriesStaff(staff);
		}else if (currentPage==Page.ReplyEnquiry){
			PageGenerator.StaffReplyEnquiry(staff);
		}else if(currentPage==Page.ViewSuggestions){
			PageGenerator.ViewSuggestionsStaff(staff);
		}else if(currentPage==Page.AcceptSuggestion){
			PageGenerator.StaffAcceptSuggestion(staff);
		}
	}
	public static void studentRedirection(Page currentPage, Student student){
		if (currentPage==Page.ViewAvailableCampsStudent)
			PageGenerator.ViewCampStudent(student);
		else if (currentPage==Page.WithdrawFromCamp)
			PageGenerator.StudentWithdrawFromCamp(student);
		else if (currentPage==Page.RegisterAttendeeCamp)
			PageGenerator.StudentRegisterAsAttendee(student);
		else if (currentPage==Page.RegisterCommitteeCamp)
			PageGenerator.StudentRegisterAsCommittee(student);
		else if (currentPage==Page.SubmitEnquiry)
			PageGenerator.StudentSubmitEnquiry(student);
		else if (currentPage==Page.ViewEnquiry)
			PageGenerator.ViewEnquiryStudent(student);
		else if (currentPage==Page.EditEnquiry)
			PageGenerator.StudentEditEnquiry(student);
		else if (currentPage==Page.ViewAllRegisteredCamps)
			PageGenerator.ViewAllRegisteredCampsStudent(student);
		else if (currentPage==Page.ViewRepliesToEnquiry)
			PageGenerator.ViewRepliesToEnquiryStudent(student);
		
		else if (currentPage==Page.ViewEnquiries)
			PageGenerator.ViewEnquiryCommittee(student);
		else if (currentPage==Page.ViewSubmittedSuggestions)
			PageGenerator.ViewSuggestionCommittee(student);
		else if(currentPage==Page.EditSuggestion){
			PageGenerator.CommitteeEditSuggestion(student);
		}else if(currentPage==Page.DeleteSuggestion){
			PageGenerator.DeleteSuggestion(student);
		}
		else if (currentPage==Page.CreateSuggestion)
			PageGenerator.CommitteeMakeSuggestion(student);
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
				//try{
					if(currentPage.equals(Page.StaffDashBoard)){
						currentPage = PageGenerator.StaffDashBoard();
					}else if (currentPage.equals(Page.StudentDashBoard)){
						currentPage = PageGenerator.StudentDashBoard(student);
					}else if (currentPage.equals(Page.ChangePassword)){
						if (u instanceof Student){
							PageGenerator.ChangePassword(student);
							currentPage = Page.StudentDashBoard;
						}
							
						else if (u instanceof Staff){
							PageGenerator.ChangePassword(staff);
							currentPage = Page.StaffDashBoard;
						}
							
					}
					
					else{
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
				//} catch (Exception e){
				// 	System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
				// 	if(u instanceof Staff) currentPage = Page.StaffDashBoard;
				// 	else currentPage = Page.StudentDashBoard;
				// }
			}
		}
	}
	
}

