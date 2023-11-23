package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.ObjectNotFoundException;

/**
 * Camp Datastore edit operator to approve a Suggestion.
 * @see IDataStoreEditOperation
 */
public class StaffApproveSuggestion implements IDataStoreEditOperation<Camp>{

    Staff staff;
    Suggestion suggestion;
    IDataStoreEditable<Student> studentDataStore;

    /**
     * Constructor for StaffApproveSuggestion
     * @param staff             Staff approving the suggestion
     * @param suggestion        Suggestion to be approved
     * @param studentDataStore  Student DataStore, required for increaing student points upon approval.
     */
    public StaffApproveSuggestion(Staff staff, Suggestion suggestion, IDataStoreEditable<Student> studentDataStore){
        this.staff = staff;
        this.suggestion = suggestion;
        this.studentDataStore = studentDataStore;
    }

    /**
     * Search for Suggestion and approve it.
     * Then call Student DataStore to increase the student's point using StudentIncreasePoint operator.
     * @param data  ArrayList of Camp from Camp DataStore.
     * @see StudentIncreasePoint
     */
    @Override
    public void perform(ArrayList<Camp> data) {
        // TODO: Check if staff has permission to approve suggestion
        // Get camp
        for (Camp camp : data) {
            if (camp.isEquals(this.suggestion.getCamp())){
                // Get suggestion
                for (Suggestion suggestion : camp.getSuggestions()){
                    if (suggestion.isEquals(this.suggestion)){
                        // Approve suggestion
                        suggestion.approve();
                        // Increase student points
                        this.studentDataStore.manageData(new StudentIncreasePoint(suggestion.getAuthor()));
                        return;
                    }
                }
                throw new ObjectNotFoundException("Suggestion", "Camp");
            }
        }
        throw new ObjectNotFoundException("Camp", "DataStore");
    }
    
}
