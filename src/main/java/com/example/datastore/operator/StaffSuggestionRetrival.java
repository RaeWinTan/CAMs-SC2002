package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Staff;
import com.example.datastructure.Suggestion;

public class StaffSuggestionRetrival implements DataStoreRetrivalOperation<Suggestion> {

    private Staff staff;

    public StaffSuggestionRetrival(Staff staff){
        this.staff = staff;
    }

    @Override
    public ArrayList<Suggestion> perform(ArrayList<Suggestion> data) {
        ArrayList<Suggestion> relevantSuggestions = new ArrayList<Suggestion>();

        // If suggestion was made for a camp created by the staff, add to relevant.
        for (Suggestion suggestion : relevantSuggestions) {
            if (suggestion.getCamp().getCreatedBy().equals(this.staff)){
                relevantSuggestions.add(suggestion);
            }
        }

        return relevantSuggestions;
    }
    
}
