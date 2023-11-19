package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Student;
import com.example.exception.ObjectNotFoundException;

public class StudentIncreasePoint implements IDataStoreEditOperation<Student>{

    Student student;
    public StudentIncreasePoint(Student student){
        this.student = student;
    }

    @Override
    public void perform(ArrayList<Student> data) {
        for (Student student : data) {
            if (student.isEquals(this.student)){
                student.increasePoints();
                return;
            }
        }
        throw new ObjectNotFoundException("Student", "DataStore");
    } 
    
}
