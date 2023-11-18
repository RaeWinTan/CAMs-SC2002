package com.example.DataServicePackage.StudentDataService;

import com.example.DataStructurePackage.Suggestion;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;

public interface IStudentSuggestionDBService {
    IMonoListDataStoreEditOperation<Suggestion> DSCreateSuggestion(Suggestion suggestion);
    IMonoListDataStoreEditOperation<Suggestion> DSDeleteSuggestion(Suggestion suggestion);
    IMonoListDataStoreEditOperation<Suggestion> DSEditSuggestion(Suggestion suggestion);
    IMonoListDataStoreRetrivalOperation<Suggestion> DSCampRetrival();
}
