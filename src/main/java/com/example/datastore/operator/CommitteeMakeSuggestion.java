package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.ObjectNotFoundException;

public class CommitteeMakeSuggestion implements IDataStoreEditOperation<Camp> {

    
    private Student student;
    private Suggestion suggestion;
    private IDataStoreEditable<Student> studentDataStore;
    public CommitteeMakeSuggestion(Student student, Suggestion suggestion, IDataStoreEditable<Student> studentDataStore){
        this.student = student;
        this.suggestion = suggestion;
        this.studentDataStore = studentDataStore;
    }

    @Override
    public void perform(ArrayList<Camp> data) {
        // TODO: Check if student is a committee memember of the camp
        // Get camp
        for (Camp camp : data) {
            if (camp.isEquals(this.suggestion.getCamp())){
                // Add Suggestion to Student
                this.studentDataStore.manageData(new CommitteeAddSuggestion(this.student, this.suggestion));
                
                // Increase student points
                this.studentDataStore.manageData(new StudentIncreasePoint(this.student));

                // Add Suggestion to Camp;
                camp.getSuggestions().add(this.suggestion);
                return;
            }
        }

        throw new ObjectNotFoundException("Camp");
    }
    
}
