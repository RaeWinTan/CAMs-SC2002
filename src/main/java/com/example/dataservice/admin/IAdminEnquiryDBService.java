package com.example.dataservice.admin;

import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;

public interface IAdminEnquiryDBService {
        IDataStoreEditOperation<Camp> DSEnquiryReply(Message reply, Enquiry enquiry);
}
