package com.example.DataServicePackage.AdminDataService;

public interface IAdminDataServicable {
    public IAdminCampDBService GetCampDBService();
    public IAdminEnquiryDBService GetEnquiryDBService();
    public IAdminSuggestionDBService GetSuggestionDBService();

}