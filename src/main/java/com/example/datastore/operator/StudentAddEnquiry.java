package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Enquiry;
import com.example.datastructure.Student;
import com.example.exception.ObjectNotFoundException;

public class StudentAddEnquiry implements IDataStoreEditOperation<Student> {

    private Student student;
    private Enquiry enquiry;

    public StudentAddEnquiry(Student student, Enquiry enquiry){
        this.student = student;
        this.enquiry = enquiry;
    }

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
