package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.IllegalOperationException;
import com.example.exception.ObjectNotFoundException;

public class CommitteeDeleteSuggestion implements IDataStoreEditOperation<Camp>{

    private Student student;
    private Suggestion suggestion;

    public CommitteeDeleteSuggestion(Student student, Suggestion suggestion){
        this.student = student;
        this.suggestion = suggestion;
    }

    @Override
    public void perform(ArrayList<Camp> data) {

        if (!this.suggestion.getAuthor().isEquals(this.student)){
            throw new IllegalOperationException("Student making suggestion does not match author in suggestion.");
        }

        // Get camp
        for (Camp camp : data) {
            if (camp.isEquals(this.suggestion.getCamp())){

                // Get suggestion
                for (Suggestion suggestion : camp.getSuggestions()) {
                    if (suggestion.isEquals(this.suggestion)){
                        
                        // Check if suggestion is approved
                        if (suggestion.getApproved()){
                            throw new IllegalOperationException("Unable to delete approved suggestions.");
                        }
                        break;
                    }
                }

                // Delete suggestion
                if (camp.getSuggestions().removeIf(suggestion->suggestion.isEquals(this.suggestion))){
                    return;
                }
                throw new ObjectNotFoundException("Suggestion");
            }   
        }
        throw new ObjectNotFoundException("Camp");
    }
}
