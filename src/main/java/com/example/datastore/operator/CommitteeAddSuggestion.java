package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.ObjectNotFoundException;

/**
 * NOTE: This class is only to be used in CommitteeMakeSuggestion.
 * Student DataStore edit Operator for creating a Suggestion by a committee member.
 * @see CommitteeMakeSuggestion
 * @see IDataStoreEditOperation
 */
public class CommitteeAddSuggestion implements IDataStoreEditOperation<Student>{

    private Student student;
    private Suggestion suggestion;

    /**
     * Constructor for CommitteeAddSuggestion.
     * @param student       Student to add suggestion to.
     * @param suggestion    Suggestion to be added.
     */
    public CommitteeAddSuggestion(Student student, Suggestion suggestion){
        this.student = student;
        this.suggestion = suggestion;
    }

    /**
     * Search for student from DataStore and append suggestion to it.
     * @param data  ArrayList of Students from Student DataStore.
     */
    @Override
    public void perform(ArrayList<Student> data) {
        // Get student
        for (Student student : data) {
            if (student.isEquals(this.student)){
                // Add suggestion to student
                student.getSuggestions().add(this.suggestion);
                return;
            }
        }
        throw new ObjectNotFoundException("Student", "DataStore");
    }
}
