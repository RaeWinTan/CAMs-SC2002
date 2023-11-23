package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.IllegalOperationException;
import com.example.exception.InsufficientPermissionException;
import com.example.exception.ObjectNotFoundException;

/**
 * Camp DataStore edit operation for deleting a suggestion.
 * @see IDataStoreEditOperation
 */
public class CommitteeDeleteSuggestion implements IDataStoreEditOperation<Camp>{

    private Student student;
    private Suggestion suggestion;
    private IDataStoreEditable<Student> studentDataStore;

    /**
     * Constructor for CommitteeDeleteSuggestion.
     * @param student           Student executing this operation.
     * @param suggestion        Suggestion to be deleted.
     * @param studentDataStore  Student DataStore, requried to remove suggestion from original Student object.
     */
    public CommitteeDeleteSuggestion(Student student, Suggestion suggestion, IDataStoreEditable<Student> studentDataStore){
        this.student = student;
        this.suggestion = suggestion;
        this.studentDataStore = studentDataStore;
    }

    /**
     * Search for Camp from DataStore and remove suggestion from it.
     * Also call studentDataStore to remove Suggestion from Student using CommitteeRemoveSuggestion operation.
     * @param data  ArrayList of Camps from Camp DataStore.
     * @see CommitteeRemoveSuggestion
     */
    @Override
    public void perform(ArrayList<Camp> data) {
        // Get camp
        for (Camp camp : data) {
            if (camp.isEquals(this.suggestion.getCamp())){
                // Get suggestion
                for (Suggestion suggestion : camp.getSuggestions()) {
                    if (suggestion.isEquals(this.suggestion)){
                        // Check if student has permission to delete suggestion
                        if (!suggestion.getAuthor().isEquals(this.student))
                            throw new InsufficientPermissionException("Student deleting suggestion does not match author in suggestion.");
                        // Check if suggestion is approved
                        if (suggestion.getApproved())
                            throw new IllegalOperationException("Unable to delete approved suggestions.");
                        break;
                    }
                }
                // Delete suggestion from student
                studentDataStore.manageData(new CommitteeRemoveSuggestion(this.student, this.suggestion));

                // Delete suggestion from camp
                if (camp.getSuggestions().removeIf(suggestion->suggestion.isEquals(this.suggestion))){
                    return;
                }
                throw new ObjectNotFoundException("Suggestion", "Camp");
            }   
        }
        throw new ObjectNotFoundException("Camp", "DataStore");
    }
}
