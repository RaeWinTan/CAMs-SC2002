package com.example.dataservice.student;

import com.example.datastore.operator.DataStoreEditOperation;
import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;

public interface IStudentEnquiryDBService {
    DataStoreEditOperation<Enquiry> DSCreateEnquiry(Enquiry enquiry);
    DataStoreEditOperation<Enquiry> DSDeleteEnquiry(Enquiry enquiry);
    DataStoreEditOperation<Enquiry> DSEditEnquiry(Enquiry enquiry);
    DataStoreEditOperation<Enquiry> DSEnquiryAddReply(Enquiry enquiry, Message reply);
    DataStoreRetrivalOperation<Enquiry> DSEnquiryRetrival();
}
