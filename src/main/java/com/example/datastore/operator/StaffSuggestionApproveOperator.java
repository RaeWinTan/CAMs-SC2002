package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Staff;
import com.example.datastructure.Suggestion;
import com.example.exception.IllegalOperationException;
import com.example.exception.InsufficientPermissionException;

public class StaffSuggestionApproveOperator implements DataStoreEditOperation<Suggestion>{


    private Suggestion suggestion;

    public StaffSuggestionApproveOperator(Staff staff, Suggestion suggestion){

        if (suggestion.getCamp().getCreatedBy().equals(staff)){
            throw new InsufficientPermissionException("Suggestion can only be approved by staff who made the camp.");
        }

        this.suggestion = suggestion;
    }


    @Override
    public void perform(ArrayList<Suggestion> data) throws IllegalOperationException {
        for (Suggestion suggestion : data) {
             if (suggestion.isEquals(this.suggestion)){
                suggestion.approve();
                // TODO: increase student points
                return;
            }
        }
    }
    
}
