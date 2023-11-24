package com.example.dataservice.admin;

import com.example.datastore.IDataStoreEditable;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;

public interface IStaffSuggestionDBService {
    IDataStoreEditOperation<Camp> DSSuggestionApprove(Suggestion suggestion, IDataStoreEditable<Student> studenttDataStore, IDataStoreEditable<Camp> campDataStore);
}
