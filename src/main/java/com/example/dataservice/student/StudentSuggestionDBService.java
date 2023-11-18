package com.example.dataservice.student;

import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.datastructure.Suggestion;

public class StudentSuggestionDBService implements IStudentSuggestionDBService{

    @Override
    public IMonoListDataStoreEditOperation<Suggestion> DSCreateSuggestion(Suggestion suggestion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSCreateSuggestion'");
    }

    @Override
    public IMonoListDataStoreEditOperation<Suggestion> DSDeleteSuggestion(Suggestion suggestion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSDeleteSuggestion'");
    }

    @Override
    public IMonoListDataStoreEditOperation<Suggestion> DSEditSuggestion(Suggestion suggestion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSEditSuggestion'");
    }

    @Override
    public IMonoListDataStoreRetrivalOperation<Suggestion> DSSuggestionRetrival() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSSuggestionRetrival'");
    }
    
}
