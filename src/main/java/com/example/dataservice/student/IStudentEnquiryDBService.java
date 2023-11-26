package com.example.dataservice.student;

import com.example.datastore.IDataStoreEditable;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.datastructure.Student;
import com.example.utility.Pair;

/**
 * Interface for Student's access to Enquiry data.
 */
public interface IStudentEnquiryDBService {
        public IDataStoreEditOperation<Camp> DSEnquiryCreate(Enquiry enquiry, IDataStoreEditable<Student> studentDSEditable);
        public IDataStoreEditOperation<Camp> DSEnquiryEdit(Enquiry newEnquiry);
        public IDataStoreEditOperation<Camp> DSEnquiryDelete(Enquiry newEnquiry, IDataStoreEditable<Student> studentDSEditable);
        public IDataStoreEditOperation<Camp> DSEnquiryReply(Pair<Enquiry,Message> em);
}
