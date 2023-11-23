package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Student;
import com.example.exception.ObjectNotFoundException;

/**
 * Student DataStore operator to increase point of student.
 * @IDataStoreEditOperation
 */
public class StudentIncreasePoint implements IDataStoreEditOperation<Student>{

    Student student;

    /**
     * Constructor for StudentIncreasePoint.
     * @param student   Student whose points to be increased.
     */
    public StudentIncreasePoint(Student student){
        this.student = student;
    }

    /**
     * Search for student and increase it's points.
     * @param data  ArrayList of Student from Student DataStore.
     */
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
