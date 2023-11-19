package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.ObjectNotFoundException;

public class StaffApproveSuggestion implements IDataStoreEditOperation<Camp>{

    Staff staff;
    Suggestion suggestion;
    IDataStoreEditable<Student> studentDataStore;
    public StaffApproveSuggestion(Staff staff, Suggestion suggestion, IDataStoreEditable<Student> studentDataStore){
        this.staff = staff;
        this.suggestion = suggestion;
        this.studentDataStore = studentDataStore;
    }

    @Override
    public void perform(ArrayList<Camp> data) {
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
                throw new ObjectNotFoundException("Suggestion");
            }
        }
        throw new ObjectNotFoundException("Camp");
    }
    
}
