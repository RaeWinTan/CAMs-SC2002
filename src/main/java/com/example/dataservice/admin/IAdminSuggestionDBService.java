package com.example.dataservice.admin;

import com.example.datastore.IDataStoreEditable;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;

public interface IAdminSuggestionDBService {
    IDataStoreEditOperation<Camp> DSSuggestionApprove(Suggestion suggestion, IDataStoreEditable<Student> studenttDataStore);
}
