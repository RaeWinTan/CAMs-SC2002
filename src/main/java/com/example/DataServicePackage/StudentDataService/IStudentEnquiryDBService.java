package com.example.DataServicePackage.StudentDataService;

import com.example.DataStructurePackage.Enquiry;
import com.example.DataStructurePackage.Message;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;

public interface IStudentEnquiryDBService {
    IMonoListDataStoreEditOperation<Enquiry> DSCreateCamp(Enquiry enquiry);
    IMonoListDataStoreEditOperation<Enquiry> DSDeleteCamp(Enquiry enquiry);
    IMonoListDataStoreEditOperation<Enquiry> DSEditCamp(Enquiry enquiry);
    IMonoListDataStoreEditOperation<Enquiry> DSEnquiryAddReply(Enquiry enquiry, Message reply);
    IMonoListDataStoreRetrivalOperation<Enquiry> DSCampRetrival();
}
