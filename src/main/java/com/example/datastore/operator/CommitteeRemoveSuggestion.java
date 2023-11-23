package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.ObjectNotFoundException;

/**
 * NOTE: This class is only to be used in CommitteeDeleteSuggestion.
 * Student DataStore edit Operator for removing a Suggestion from a Student.
 * @see CommitteeDeleteSuggestion
 * @see IDataStoreEditOperation
 */
public class CommitteeRemoveSuggestion implements IDataStoreEditOperation<Student> {

    private Student student;
    private Suggestion suggestion;

    /**
     * Constructor for CommitteeRemoveSuggestion.
     * @param student       Student to remove suggestion from.
     * @param suggestion    Suggestion being removed.
     */
    public CommitteeRemoveSuggestion(Student student, Suggestion suggestion){
        this.student = student;
        this.suggestion = suggestion;
    }

    /**
     * Search for student and remove suggestion from it.
     * @param data  ArrayList of Students from Student DataStore.
     */
    @Override
    public void perform(ArrayList<Student> data) {
        // Get student
        for (Student student : data) {
            if (student.isEquals(this.student)){

                // Remove suggestion
                if (student.getSuggestions().removeIf(suggestion->suggestion.isEquals(this.suggestion))){
                    return;
                }
                throw new ObjectNotFoundException("Suggestion", "Student");
            }
        }
        throw new ObjectNotFoundException("Student", "DataStore");
    }
}
