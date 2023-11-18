package com.example.dataservice.admin;

import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;

public class AdminEnquiryDBService implements IAdminEnquiryDBService {

    @Override
    public IMonoListDataStoreEditOperation<Enquiry> DSEnquiryAddReply(Enquiry enquiry, Message reply) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSEnquiryAddReply'");
    }

    @Override
    public IMonoListDataStoreRetrivalOperation<Enquiry> DSEnquiryRetrival() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSEnquiryRetrival'");
    }
    
}
