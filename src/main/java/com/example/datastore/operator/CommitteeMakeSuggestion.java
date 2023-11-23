package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.ObjectNotFoundException;

/**
 * Camp DataStore edit operator for making a Suggestion.
 * @see IDataStoreEditOperation
 */
public class CommitteeMakeSuggestion implements IDataStoreEditOperation<Camp> {

    private Student student;
    private Suggestion suggestion;
    private IDataStoreEditable<Student> studentDataStore;

    /**
     * Constructor for CommitteeMakeSuggestion.
     * @param student           Student making the suggestion.
     * @param suggestion        Suggestion being made.
     * @param studentDataStore  Student DataStore, required for appending suggestion to original Student object.
     */
    public CommitteeMakeSuggestion(Student student, Suggestion suggestion, IDataStoreEditable<Student> studentDataStore){
        this.student = student;
        this.suggestion = suggestion;
        this.studentDataStore = studentDataStore;
    }

    /**
     * Append suggestion to appropriate Camp from Camp DataStore.
     * Also call studentDataStore to append suggestion to student using CommitteeAddSuggestion operation.
     * @param data  ArrayList of Camps from Camp DataStore.
     * @see CommitteeAddSuggestion
     */
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
        throw new ObjectNotFoundException("Camp", "DataStore");
    }
}
