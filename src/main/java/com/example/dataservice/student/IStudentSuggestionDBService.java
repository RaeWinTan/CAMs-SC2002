package com.example.dataservice.student;

import com.example.datastore.operator.DataStoreEditOperation;
import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.datastructure.Suggestion;

public interface IStudentSuggestionDBService {
    DataStoreEditOperation<Suggestion> DSCreateSuggestion(Suggestion suggestion);
    DataStoreEditOperation<Suggestion> DSDeleteSuggestion(Suggestion suggestion);
    DataStoreEditOperation<Suggestion> DSEditSuggestion(Suggestion suggestion);
    DataStoreRetrivalOperation<Suggestion> DSSuggestionRetrival();
}
