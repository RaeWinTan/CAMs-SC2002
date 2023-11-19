package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;
import com.example.exception.IllegalOperationException;

public class StudentAddLeadingCamp implements IDataStoreEditOperation<Student> {

    private Student student;
    private Camp camp;

    public StudentAddLeadingCamp(Student student, Camp camp){
        this.student = student;
        this.camp = camp;
    }

    @Override
    public void perform(ArrayList<Student> data) {
        for (Student student : data) {
            if (student.isEquals(this.student)){
                student.getLeading().add(new CampMember(this.student, this.camp));
                return;
            }
        }

        throw new IllegalOperationException("Student not found.");
    }
    
}
