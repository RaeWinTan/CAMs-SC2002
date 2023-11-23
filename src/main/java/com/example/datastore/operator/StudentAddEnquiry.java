package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Enquiry;
import com.example.datastructure.Student;
import com.example.exception.ObjectNotFoundException;

/**
 * NOTE: This class is only to be used in StudentEnquiryCreate.
 * Student DataStore edit operator to add Enquiry to Student.
 * @see StudentEnquiryCreate
 * @see IDataStoreEditOperation
 */
public class StudentAddEnquiry implements IDataStoreEditOperation<Student> {

    private Student student;
    private Enquiry enquiry;

    /**
     * Constructor for StudentAddEnquiry
     * @param student   Student to add enquiry to.
     * @param enquiry   Enquiry to be added.
     */
    public StudentAddEnquiry(Student student, Enquiry enquiry){
        this.student = student;
        this.enquiry = enquiry;
    }

    /**
     * Search for student and add enquiry to it.
     * @param data  ArrayList of Students from Student DataStore.
     */
    @Override
    public void perform(ArrayList<Student> data) {
        // get student
        for (Student student : data) {
            if (student.isEquals(this.student)){
                // add enquiry
                student.getEnquireAbout().add(this.enquiry);
                return;
            }
        }
        throw new ObjectNotFoundException("Student", "DataStore");
    }
}
