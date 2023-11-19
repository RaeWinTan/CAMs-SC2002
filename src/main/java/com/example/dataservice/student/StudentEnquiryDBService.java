package com.example.dataservice.student;

import com.example.datastore.operator.DataStoreEditOperation;
import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;

public class StudentEnquiryDBService implements IStudentEnquiryDBService {

    @Override
    public DataStoreEditOperation<Enquiry> DSCreateEnquiry(Enquiry enquiry) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSCreateEnquiry'");
    }

    @Override
    public DataStoreEditOperation<Enquiry> DSDeleteEnquiry(Enquiry enquiry) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSDeleteEnquiry'");
    }

    @Override
    public DataStoreEditOperation<Enquiry> DSEditEnquiry(Enquiry enquiry) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSEditEnquiry'");
    }

    @Override
    public DataStoreEditOperation<Enquiry> DSEnquiryAddReply(Enquiry enquiry, Message reply) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSEnquiryAddReply'");
    }

    @Override
    public DataStoreRetrivalOperation<Enquiry> DSEnquiryRetrival() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSEnquiryRetrival'");
    }
    
}
