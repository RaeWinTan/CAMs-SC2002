package com.example.datastore.monolist.operator;

import java.util.ArrayList;

import com.example.datastructure.Enquiry;
import com.example.datastructure.Reply;
import com.example.exception.IllegalOperationException;

public class StaffEnquiryReplyOperator implements IMonoListDataStoreEditOperation<Enquiry> {

    // TODO: check if reply or message is stored
    public StaffEnquiryReplyOperator(Enquiry enquiry, Reply reply){
        
    }

    @Override
    public void perform(ArrayList<Enquiry> data) throws IllegalOperationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'perform'");
    }
    
}
