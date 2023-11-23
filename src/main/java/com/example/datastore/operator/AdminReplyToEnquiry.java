package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.datastructure.User;
import com.example.exception.IllegalOperationException;
import com.example.exception.ObjectNotFoundException;

/**
 * Camp Datastore edit operator for creating a reply to an enquiry.
 * @see IDataStoreEditOperation
 */
public class AdminReplyToEnquiry implements IDataStoreEditOperation<Camp>{

    private User user;
    private Message reply;
    private Enquiry enquiry;

    /**
     * Constructor for AdminReplyToEnquiry operator.
     * @param user      User executing this operation.
     * @param reply     Message representing the reply to an enquiry.
     * @param enquiry   Copy of the enquiry being replied to.
     */
    public AdminReplyToEnquiry(User user, Message reply, Enquiry enquiry){
        this.user = user;
        this.reply = reply;
        this.enquiry = enquiry;
    }

    /**
     * Search for the enquiry from Camp DataStore and appends a Message representing a reply to it.
     * @param data  ArrayList of Camps from Camp DataStore.
     */
    @Override
    public void perform(ArrayList<Camp> data) {
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
                throw new ObjectNotFoundException("Enquiry", "Camp");
            }
        }
        throw new ObjectNotFoundException("Camp", "DataStore");
    }
    
}
