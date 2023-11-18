package com.example.dataservice;

import com.example.dataservice.admin.AdminCampDBService;
import com.example.dataservice.admin.AdminEnquiryDBService;
import com.example.dataservice.admin.AdminSuggestionDBService;
import com.example.dataservice.admin.IAdminCampDBService;
import com.example.dataservice.admin.IAdminDataServicable;
import com.example.dataservice.admin.IAdminEnquiryDBService;
import com.example.dataservice.admin.IAdminSuggestionDBService;
import com.example.datastructure.Staff;

public class StaffDBService extends UserDBService<Staff> implements IAdminDataServicable {

	private IAdminCampDBService campDBService;
	private IAdminEnquiryDBService enquiryDBService;
	private IAdminSuggestionDBService suggestionDBService;
	
	public StaffDBService(){
		this.campDBService = new AdminCampDBService();
		this.enquiryDBService = new AdminEnquiryDBService();
		this.suggestionDBService = new AdminSuggestionDBService();
	}

	@Override
	public IAdminCampDBService GetCampDBService() {
		return this.campDBService;
	}
	@Override
	public IAdminEnquiryDBService GetEnquiryDBService() {
		return this.enquiryDBService;
	}
	@Override
	public IAdminSuggestionDBService GetSuggestionDBService() {
		return this.suggestionDBService;
	}
}