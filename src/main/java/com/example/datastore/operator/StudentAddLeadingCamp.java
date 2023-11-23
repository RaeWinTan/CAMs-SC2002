package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;
import com.example.exception.ObjectNotFoundException;

/**
 * NOTE: This class is only to be used in StudentJoinCampAsCommittee.
 * Student DataStore edit operator to add attending camp to Student.
 * @see StudentJoinCampAsCommittee
 * @see IDataStoreEditOperation
 */
public class StudentAddLeadingCamp implements IDataStoreEditOperation<Student> {

    private Student student;
    private Camp camp;

    /**
     * Constructor for StudentAddLeadingCamp
     * @param student   Student to add camp to.
     * @param camp      Camp to be added.
     */
    public StudentAddLeadingCamp(Student student, Camp camp){
        this.student = student;
        this.camp = camp;
    }

    /**
     * Search for Student and add Camp to leading ArrayList.
     * @param data  ArrayList of Student from Student DataStore.
     */
    @Override
    public void perform(ArrayList<Student> data) {
        for (Student student : data) {
            if (student.isEquals(this.student)){
                student.getLeading().add(new CampMember(this.student, this.camp));
                return;
            }
        }

        throw new ObjectNotFoundException("Student", "DataStore");
    }
    
}
