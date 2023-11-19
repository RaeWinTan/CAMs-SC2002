package com.example.dataservice.admin;

import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.datastructure.Suggestion;

public interface IAdminSuggestionDBService {
    // TODO: Implement this operations
    IMonoListDataStoreEditOperation<Suggestion> DSApproveSuggestion(Suggestion suggestion);
    IMonoListDataStoreRetrivalOperation<Suggestion> DSSuggestionRetrival();
}
