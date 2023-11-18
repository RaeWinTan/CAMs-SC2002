package com.example.dataservice;

import com.example.dataservice.student.IStudentCampDBService;
import com.example.dataservice.student.IStudentDataServicable;
import com.example.dataservice.student.IStudentEnquiryDBService;
import com.example.dataservice.student.IStudentSuggestionDBService;
import com.example.dataservice.student.StudentCampDBService;
import com.example.dataservice.student.StudentEnquiryDBService;
import com.example.dataservice.student.StudentSuggestionDBService;
import com.example.datastructure.Student;

public class StudentDBService extends UserDBService<Student> implements IStudentDataServicable {

	private IStudentCampDBService campDBService;
	private IStudentEnquiryDBService enquiryDBService;
	private IStudentSuggestionDBService suggestionDBService;

	public StudentDBService(Student s) {
		this.campDBService = new StudentCampDBService(s);
		this.enquiryDBService = new StudentEnquiryDBService();
		this.suggestionDBService = new StudentSuggestionDBService();
	}

	@Override
	public IStudentCampDBService GetCampDBService() {
		return this.campDBService;
	}

	@Override
	public IStudentEnquiryDBService GetEnquiryDBService() {
		return this.enquiryDBService;
	}

	@Override
	public IStudentSuggestionDBService GetSuggestionDBService() {
		return this.suggestionDBService;
	}
}