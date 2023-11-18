package com.example.DataServicePackage.AdminDataService;

import com.example.DataStructurePackage.Enquiry;
import com.example.DataStructurePackage.Message;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;

public interface IAdminEnquiryDBService {
    // TODO: Implement these operations
    IMonoListDataStoreEditOperation<Enquiry> DSEnquiryAddReply(Enquiry enquiry, Message reply);
    IMonoListDataStoreRetrivalOperation<Enquiry> DSEnquiryRetrival();
}
