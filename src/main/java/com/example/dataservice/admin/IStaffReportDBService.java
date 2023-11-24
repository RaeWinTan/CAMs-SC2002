package com.example.dataservice.admin;

import com.example.datastore.IDataStoreRetrivable;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;

public interface IStaffReportDBService {
	public IDataStoreEditOperation<Camp> DSGenerateEnquiryReport(String fileName);
	public IDataStoreEditOperation<Camp> DSGeneratePerformanceReport(String fileName, IDataStoreRetrivable<Student> studentDSRetrivable);
	public IDataStoreEditOperation<Camp> DSGenerateParticipantReport(String fileName);
}
