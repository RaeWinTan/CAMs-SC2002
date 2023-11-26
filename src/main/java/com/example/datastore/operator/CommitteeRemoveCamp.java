package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.exception.ObjectNotFoundException;

/**
 * Student DataStore edit Operator for removing a Camp from a Student.
 * NOTE: This class is only to be used in StaffCampDelete.
 * @see StaffCampDelete
 * @see IDataStoreEditOperation
 */
public class CommitteeRemoveCamp implements IDataStoreEditOperation<Student> {
    Student student;
    Camp camp;

    /**
     * Constructor for AttendeeRemoveCamp
     * @param student   Student to delete camp from.
     * @param camp      Camp to be deleted.
     */
    public CommitteeRemoveCamp(Student student, Camp camp){
        this.student = student;
        this.camp = camp;
    }

    @Override
    public void perform(ArrayList<Student> data) {
        for (Student student : data) {
            if (student.isEquals(this.student)){
                student.getSuggestions().removeIf(suggestion->suggestion.getCamp().isEquals(this.camp)); // possible to have no suggestions
                if (!student.getLeading().removeIf(cm->cm.getCamp().isEquals(this.camp)))
                    throw new ObjectNotFoundException("Camp", "Student");
                return;
            }
        }
        throw new ObjectNotFoundException("Student", "DataStore");
    }
}
