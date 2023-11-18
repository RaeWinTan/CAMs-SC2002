package com.example.dataservice.student;

import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;

public class StudentEnquiryDBService implements IStudentEnquiryDBService {

    @Override
    public IMonoListDataStoreEditOperation<Enquiry> DSCreateEnquiry(Enquiry enquiry) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSCreateEnquiry'");
    }

    @Override
    public IMonoListDataStoreEditOperation<Enquiry> DSDeleteEnquiry(Enquiry enquiry) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSDeleteEnquiry'");
    }

    @Override
    public IMonoListDataStoreEditOperation<Enquiry> DSEditEnquiry(Enquiry enquiry) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSEditEnquiry'");
    }

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
