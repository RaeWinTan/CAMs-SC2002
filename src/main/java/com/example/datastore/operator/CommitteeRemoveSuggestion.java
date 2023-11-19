package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.ObjectNotFoundException;

public class CommitteeRemoveSuggestion implements IDataStoreEditOperation<Student> {

    private Student student;
    private Suggestion suggestion;

    public CommitteeRemoveSuggestion(Student student, Suggestion suggestion){
        this.student = student;
        this.suggestion = suggestion;
    }

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
