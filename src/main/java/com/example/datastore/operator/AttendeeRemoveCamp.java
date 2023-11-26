package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.exception.ObjectNotFoundException;

/**
 * Student DataStore edit Operator for removing a Camp from a Student.
 * NOTE: This class is only to be used in AttendeeCampQuit.
 * @see AttendeeCampQuit
 * @see IDataStoreEditOperation
 */
public class AttendeeRemoveCamp implements IDataStoreEditOperation<Student>{

    Student student;
    Camp camp;

    /**
     * Constructor for AttendeeRemoveCamp
     * @param student   Student to delete camp from.
     * @param camp      Camp to be deleted.
     */
    public AttendeeRemoveCamp(Student student, Camp camp){
        this.student = student;
        this.camp = camp;
    }

    /**
     * Remove camp from student attending list.
     * @param data      ArrayList of Student from Student DataStore.
     */
    @Override
    public void perform(ArrayList<Student> data) {
        for (Student student : data) {
            if (student.isEquals(this.student)){
                if (student.getAttending().removeIf(cm->cm.getCamp().isEquals(this.camp))){
                    return;
                }
                throw new ObjectNotFoundException("Camp", "Student");
            }
        }
        throw new ObjectNotFoundException("Student", "DataStore");
    }
}
