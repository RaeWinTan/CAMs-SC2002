package com.example.dataservice.student;

import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.datastructure.Suggestion;

public interface IStudentSuggestionDBService {
    IMonoListDataStoreEditOperation<Suggestion> DSCreateSuggestion(Suggestion suggestion);
    IMonoListDataStoreEditOperation<Suggestion> DSDeleteSuggestion(Suggestion suggestion);
    IMonoListDataStoreEditOperation<Suggestion> DSEditSuggestion(Suggestion suggestion);
    IMonoListDataStoreRetrivalOperation<Suggestion> DSSuggestionRetrival();
}
