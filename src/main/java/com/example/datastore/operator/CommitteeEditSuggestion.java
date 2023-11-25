package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.IllegalOperationException;
import com.example.exception.InsufficientPermissionException;
import com.example.exception.ObjectNotFoundException;

/**
 * Camp DataStore edit operation for editing a suggestion.
 * @see IDataStoreEditOperation
 */
public class CommitteeEditSuggestion implements IDataStoreEditOperation<Camp>{

    private Student student;
    private Suggestion newSuggestion;

    /**
     * Constructor for CommitteeEditSuggestion.
     * @param student       Student executing this operation
     * @param newSuggestion Suggestion with updated values
     */
    public CommitteeEditSuggestion(Student student, Suggestion newSuggestion){
        this.student = student;
        this.newSuggestion = newSuggestion;
    }

    /**
     * Search for Suggestion and update the Camp within it.
     * @param data  ArrayList of Camps from Camp DataStore.
     */
    @Override
    public void perform(ArrayList<Camp> data) {
        // Get camp
        for (Camp camp : data) {
            if (camp.isEquals(newSuggestion.getCamp())){

                // Get suggestion
                for (Suggestion suggestion : camp.getSuggestions()) {
                    if (suggestion.isEquals(this.newSuggestion)){

                        if (suggestion.getApproved()){
                            throw new IllegalOperationException("Cannot edit suggestion after it has been approved.");
                        }

                        // Check credentials
                        if (!suggestion.getAuthor().isEquals(this.student))
                            throw new InsufficientPermissionException("Student making suggestion does not match author in suggestion.");

                        // Update suggestion
                        // Camp should also be updated in student
                        suggestion.getCamp().setAll(this.newSuggestion.getCamp());
                        return;
                    }
                }
                throw new ObjectNotFoundException("Suggestion", "Camp");
            }
        }
        throw new ObjectNotFoundException("Camp", "DataStore");
    }
    
}
