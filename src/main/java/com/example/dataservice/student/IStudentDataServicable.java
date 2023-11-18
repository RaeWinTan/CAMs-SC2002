package com.example.dataservice.student;

public interface IStudentDataServicable {
    public IStudentCampDBService GetCampDBService();
    public IStudentEnquiryDBService GetEnquiryDBService();
    public IStudentSuggestionDBService GetSuggestionDBService();
}
