package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.InsufficientPermissionException;
import com.example.exception.ObjectNotFoundException;

public class CommitteeEditSuggestion implements IDataStoreEditOperation<Camp>{


    private Student student;
    private Suggestion suggestion;

    public CommitteeEditSuggestion(Student student, Suggestion suggestion){
        this.student = student;
        this.suggestion = suggestion;
    }

    @Override
    public void perform(ArrayList<Camp> data) {
        // Get camp
        for (Camp camp : data) {
            if (camp.isEquals(suggestion.getCamp())){

                // Get suggestion
                for (Suggestion suggestion : camp.getSuggestions()) {
                    if (suggestion.isEquals(this.suggestion)){

                        // Check credentials
                        if (!suggestion.getAuthor().isEquals(this.student))
                            throw new InsufficientPermissionException("Student making suggestion does not match author in suggestion.");

                        // Update suggestion
                        suggestion.getCamp().setAll(this.suggestion.getCamp());
                        return;
                    }
                }
                throw new ObjectNotFoundException("Suggestion", "Camp");
            }
        }

        throw new ObjectNotFoundException("Camp", "DataStore");
    }
    
}
