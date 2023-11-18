package com.example.DataServicePackage.AdminDataService;

import com.example.DataStructurePackage.Staff;
import com.example.DataStructurePackage.Suggestion;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;

public interface IAdminSuggestionDBService {
    // TODO: Implement this operations
    IMonoListDataStoreEditOperation<Suggestion> DSApproveSuggestion(Suggestion suggestion);
    IMonoListDataStoreRetrivalOperation<Suggestion> DSSuggestionRetrival(Staff staff);
}
