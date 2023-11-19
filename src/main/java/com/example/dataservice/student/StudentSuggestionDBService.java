package com.example.dataservice.student;

import com.example.datastore.operator.DataStoreEditOperation;
import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.datastructure.Suggestion;

public class StudentSuggestionDBService implements IStudentSuggestionDBService{

    @Override
    public DataStoreEditOperation<Suggestion> DSCreateSuggestion(Suggestion suggestion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSCreateSuggestion'");
    }

    @Override
    public DataStoreEditOperation<Suggestion> DSDeleteSuggestion(Suggestion suggestion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSDeleteSuggestion'");
    }

    @Override
    public DataStoreEditOperation<Suggestion> DSEditSuggestion(Suggestion suggestion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSEditSuggestion'");
    }

    @Override
    public DataStoreRetrivalOperation<Suggestion> DSSuggestionRetrival() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSSuggestionRetrival'");
    }
    
}
