package com.example.dataservice.admin;

import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.datastructure.Staff;
import com.example.datastructure.Suggestion;

public class AdminSuggestionDBService implements IAdminSuggestionDBService {

    @Override
    public IMonoListDataStoreEditOperation<Suggestion> DSApproveSuggestion(Suggestion suggestion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSApproveSuggestion'");
    }

    @Override
    public IMonoListDataStoreRetrivalOperation<Suggestion> DSSuggestionRetrival(Staff staff) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSSuggestionRetrival'");
    }
    
}
