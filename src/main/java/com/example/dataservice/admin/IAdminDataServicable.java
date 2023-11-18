package com.example.dataservice.admin;

public interface IAdminDataServicable {
    public IAdminCampDBService GetCampDBService();
    public IAdminEnquiryDBService GetEnquiryDBService();
    public IAdminSuggestionDBService GetSuggestionDBService();

}