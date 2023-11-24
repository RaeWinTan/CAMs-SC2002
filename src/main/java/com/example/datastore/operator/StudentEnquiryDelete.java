package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Student;
import com.example.exception.ObjectNotFoundException;

/**
 * Camp DataStore edit operation for deleting an enquiry.
 * @see IDataStoreEditOperation
 */
public class StudentEnquiryDelete implements IDataStoreEditOperation<Camp>{

    Student student;
    Enquiry enquiry;
    IDataStoreEditable<Student> studentDSEditable;

    /**
     * Constructor for StudentEnquiryDelete
     * @param student           Student performing the operation
     * @param enquiry           Enquiry to be deleted
     * @param studentDSEditable Student DataStore, required to remove Enquiry from Student.
     */
    public StudentEnquiryDelete(Student student, Enquiry enquiry, IDataStoreEditable<Student> studentDSEditable){
        this.student = student;
        this.enquiry = enquiry;
        this.studentDSEditable = studentDSEditable;
    }

    /**
     * Remove enquiry from camp
     * Call Student DataStore to remove Enquiry from Student using StudentRemoveEnquiry.
     * @param data  ArrayList of Camp from Camp DataStore.
     * @see StudentRemoveEnquiry
     */
    @Override
    public void perform(ArrayList<Camp> data) {
        for (Camp camp : data) {
            if (camp.equals(this.enquiry.getCamp())){
                studentDSEditable.manageData(new StudentRemoveEnquiry(this.student, this.enquiry));
                if (camp.getEnquiries().removeIf(enquiry->enquiry.isEquals(this.enquiry))){
                    return;
                }
                throw new ObjectNotFoundException("Enquiry", "Camp");
            }
        }
        throw new ObjectNotFoundException("Camp", "Datastore");
    }
    
}
