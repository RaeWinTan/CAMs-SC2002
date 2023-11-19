package com.example.dataservice.admin;

import com.example.datastore.operator.DataStoreEditOperation;
import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.datastructure.Suggestion;

public interface IAdminSuggestionDBService {
    // TODO: Implement this operations
    DataStoreEditOperation<Suggestion> DSApproveSuggestion(Suggestion suggestion);
    DataStoreRetrivalOperation<Suggestion> DSSuggestionRetrival();
}
