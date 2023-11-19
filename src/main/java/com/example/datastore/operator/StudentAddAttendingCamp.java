package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;
import com.example.exception.IllegalOperationException;

public class StudentAddAttendingCamp implements DataStoreEditOperation<Student>{

    private Student student;
    private Camp camp;

    public StudentAddAttendingCamp(Student student, Camp camp){
        this.student = student;
        this.camp = camp;
    }

    @Override
    public void perform(ArrayList<Student> data) throws IllegalOperationException {
        for (Student student : data) {
            if (student.isEquals(this.student)){
                student.getAttending().add(new CampMember(this.student, this.camp));
                return;
            }
        }

        throw new IllegalOperationException("Student not found.");
    }
}
