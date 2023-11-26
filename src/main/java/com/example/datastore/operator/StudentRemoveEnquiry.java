package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Enquiry;
import com.example.datastructure.Student;
import com.example.exception.ObjectNotFoundException;

/**
 * Student DataStore edit Operator for removing an Enquiry from a Student.
 * NOTE: This class is only to be used in StudentEnquiryDelete.
 * @see StudentEnquiryDelete
 * @see IDataStoreEditOperation
 */
public class StudentRemoveEnquiry implements IDataStoreEditOperation<Student> {

    Student student;
    Enquiry enquiry;

    /**
     * Constructor for StudentRemoveEnquiry.
     * @param student   Student to remove enquiry from.
     * @param enquiry   Enquiry to be removed.
     */
    public StudentRemoveEnquiry(Student student, Enquiry enquiry){
        this.student = student;
        this.enquiry = enquiry;
    }

    /**
     * Remove enquiry from student.
     * @param data  ArrayList of Students from Student DataStore.
     */
    @Override
    public void perform(ArrayList<Student> data) {
        for (Student student : data) {
            if (student.isEquals(this.student)){
                if (student.getEnquireAbout().removeIf(enquiry->enquiry.isEquals(this.enquiry))){
                    return;
                }
                throw new ObjectNotFoundException("Enquiry", "Student");
            }
        }
        throw new ObjectNotFoundException("Student", "DataStore");
    }

}
