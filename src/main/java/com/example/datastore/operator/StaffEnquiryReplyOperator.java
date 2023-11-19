package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.datastructure.Staff;
import com.example.exception.IllegalOperationException;
import com.example.exception.InsufficientPermissionException;

/**
 * Operator for staff to reply to enquiry.
 */
public class StaffEnquiryReplyOperator implements DataStoreEditOperation<Enquiry> {

    private Enquiry enquiry;
    private Message reply;

    /**
     * Constructor for StaffEnquiryReplyOperator
     * @param staff     Staff making the reply- used for checking
     * @param enquiry   Enqiry to add the reply to
     * @param reply     Message object 
     */
    public StaffEnquiryReplyOperator(Staff staff, Enquiry enquiry, Message reply){
        if (!staff.isEquals(enquiry.getCamp().getCreatedBy())){
            throw new InsufficientPermissionException("Staff can only reply to enquiries to camps create by themselves.");
        }
        if (!staff.isEquals(reply.getAuthor())){
            throw new IllegalOperationException("Staff adding the reply does not match author of the reply");
        }
        this.enquiry = enquiry;
        this.reply = reply;
    }

    @Override
    public void perform(ArrayList<Enquiry> data) throws IllegalOperationException {
        for (Enquiry enquiry : data) {
            if (enquiry.isEquals(this.enquiry)){
                enquiry.addReply(reply);
                return;
            }
        }
        throw new IllegalOperationException("Enquiry not found.");
    }
    
}
