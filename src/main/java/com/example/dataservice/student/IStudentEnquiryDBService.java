package com.example.dataservice.student;

import com.example.datastore.IDataStoreEditable;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Student;

public interface IStudentEnquiryDBService {
        public IDataStoreEditOperation<Camp> DSEnquiryCreate(Enquiry enquiry, IDataStoreEditable<Student> studentDataStore);
}
