package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Student;
import com.example.exception.IllegalOperationException;
import com.example.exception.ObjectNotFoundException;

/**
 * Camp DataStore edit operation for editing a enquiry.
 * @see IDataStoreEditOperation
 */
public class StudentEnquiryEdit implements IDataStoreEditOperation<Camp>{
    Student student;
    Enquiry newEnquiry;

    /**
     * Constructor for StudentEnquiryEdit
     * @param student       Student editing the enquiry
     * @param newEnquiry    Enquiry with updated values.
     */
    public StudentEnquiryEdit(Student student, Enquiry newEnquiry){
        this.student = student;
        this.newEnquiry = newEnquiry;
    }

    /**
     * Search for enquiry and edit it.
     * @param data ArrayList of Camps from Camp DataStore
     */
    @Override
    public void perform(ArrayList<Camp> data) {
        // Get camp
        for (Camp camp : data) {
            if (camp.isEquals(this.newEnquiry.getCamp())){
                // Get enquiry
                for (Enquiry enquiry : camp.getEnquiries()){
                    if (enquiry.isEquals(this.newEnquiry)){
                        if (!enquiry.getReplies().isEmpty())
                            throw new IllegalOperationException("Cannot modify enquiries with replies.");
                        // Update enquiry
                        enquiry.setText(this.newEnquiry.getText());
                        return;
                    }
                }
                throw new ObjectNotFoundException("Enquiry","Camp");
            }
        }
        throw new ObjectNotFoundException("Camp","DataStore");
    }
}
