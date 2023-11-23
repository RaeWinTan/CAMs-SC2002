package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Student;
import com.example.exception.ObjectNotFoundException;

/**
 * Camp DataStore edit operator for creating an enquiry.
 * @see IDataStoreEditOperation
 */
public class StudentEnquiryCreate implements IDataStoreEditOperation<Camp> {

    Student student;
    Enquiry enquiry;
    IDataStoreEditable<Student> studentDataStore;

    /**
     * Constructor for StudentEnquiryCreate
     * @param student
     * @param enquiry
     * @param studentDataStore
     */
    public StudentEnquiryCreate(Student student, Enquiry enquiry, IDataStoreEditable<Student> studentDataStore){
        this.student = student;
        this.enquiry = enquiry;
        this.studentDataStore = studentDataStore;
    }

    /**
     * Search for camp and add enquiry to it.
     * Call Student DataStore to add enquiry to Student using StudentAddEnquiry.
     * @param data  ArrayList of Camp from Camp DataStore
     * @see StudentAddEnquiry
     */
    @Override
    public void perform(ArrayList<Camp> data) {
        // Get camp
        for (Camp camp : data) {
            if (camp.isEquals(this.enquiry.getCamp())){
                // add enquiry to student
                studentDataStore.manageData(new StudentAddEnquiry(this.student, this.enquiry));
                // add enquiry to camp
                return;
            }
        }
        throw new ObjectNotFoundException("Camp", "DataStore");
    }
    
}
