package com.example.dataservice.admin;

import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.utility.Pair;

public interface IStaffEnquiryDBService {
        IDataStoreEditOperation<Camp> DSEnquiryReply(Pair<Enquiry,Message> em);
}
