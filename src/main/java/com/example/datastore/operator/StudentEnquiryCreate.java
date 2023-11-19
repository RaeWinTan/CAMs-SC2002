package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Student;
import com.example.exception.ObjectNotFoundException;

public class StudentEnquiryCreate implements IDataStoreEditOperation<Camp> {

    Student student;
    Enquiry enquiry;
    IDataStoreEditable<Student> studentDataStore;
    public StudentEnquiryCreate(Student student, Enquiry enquiry, IDataStoreEditable<Student> studentDataStore){
        this.student = student;
        this.enquiry = enquiry;
        this.studentDataStore = studentDataStore;
    }

    @Override
    public void perform(ArrayList<Camp> data) {


        // Get camp
        for (Camp camp : data) {
            if (camp.isEquals(this.enquiry.getCamp())){
                // add enquiry to student
                studentDataStore.manageData(new StudentAddEnquiry(this.student, this.enquiry));
                // add enquiry to camp
                camp.getEnquiries().add(this.enquiry);
                return;
            }
        }
        throw new ObjectNotFoundException("Camp");
    }
    
}
