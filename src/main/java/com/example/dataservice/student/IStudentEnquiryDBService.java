package com.example.dataservice.student;

import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;

public interface IStudentEnquiryDBService {
    IMonoListDataStoreEditOperation<Enquiry> DSCreateEnquiry(Enquiry enquiry);
    IMonoListDataStoreEditOperation<Enquiry> DSDeleteEnquiry(Enquiry enquiry);
    IMonoListDataStoreEditOperation<Enquiry> DSEditEnquiry(Enquiry enquiry);
    IMonoListDataStoreEditOperation<Enquiry> DSEnquiryAddReply(Enquiry enquiry, Message reply);
    IMonoListDataStoreRetrivalOperation<Enquiry> DSEnquiryRetrival();
}
