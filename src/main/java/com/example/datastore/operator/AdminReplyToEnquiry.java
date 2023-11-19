package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.datastructure.User;
import com.example.exception.IllegalOperationException;
import com.example.exception.ObjectNotFoundException;

public class AdminReplyToEnquiry implements IDataStoreEditOperation<Camp>{

    private User user;
    private Message reply;
    private Enquiry enquiry;

    public AdminReplyToEnquiry(User user, Message reply, Enquiry enquiry){
        this.user = user;
        this.reply = reply;
        this.enquiry = enquiry;
    }

    @Override
    public void perform(ArrayList<Camp> data) {
        // TODO: Check if user has permission (possibly creeate base classes for student (to check committee) and user (to cehck if camp was created by them))
        // check user matches reply author
        if (!reply.getAuthor().isEquals(this.user)){
            throw new IllegalOperationException("User replying does not match author of the message.");
        }

        // get camp from datastore
        for (Camp camp : data) {
            if (camp.isEquals(enquiry.getCamp())){

                // get enquiry from camp
                for (Enquiry enquiry : camp.getEnquiries()) {
                    if (enquiry.isEquals(this.enquiry)){

                        // add reply woohoo
                        enquiry.getReplies().add(this.reply);
                        return;
                    }
                }
                throw new ObjectNotFoundException("Enquiry");
            }
        }
        throw new ObjectNotFoundException("Camp");
    }
    
}
