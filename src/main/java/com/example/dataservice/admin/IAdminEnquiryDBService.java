package com.example.dataservice.admin;

import com.example.datastore.operator.DataStoreEditOperation;
import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;

public interface IAdminEnquiryDBService {
    // TODO: Implement these operations
    DataStoreEditOperation<Enquiry> DSEnquiryAddReply(Enquiry enquiry, Message reply);
    DataStoreRetrivalOperation<Enquiry> DSEnquiryRetrival();
}
