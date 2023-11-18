package com.example.DataServicePackage.StudentDataService;

public interface IStudentDataServicable {
    public IStudentCampDBService GetCampDBService();
    public IStudentEnquiryDBService GetEnquiryDBService();
    public IStudentSuggestionDBService GetSuggestionDBService();
}
