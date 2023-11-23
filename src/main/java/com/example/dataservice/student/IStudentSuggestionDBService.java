package com.example.dataservice.student;

import com.example.datastore.IDataStoreEditable;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;

public interface IStudentSuggestionDBService {
    public IDataStoreEditOperation<Camp> DSSuggestionCreate(Suggestion suggestion, IDataStoreEditable<Student> studentDataStore);

    public IDataStoreEditOperation<Camp> DSSuggestioNDelete(Suggestion suggestion, IDataStoreEditable<Student> studentDataStore);

    public IDataStoreEditOperation<Camp> DSSuggestionEdit(Suggestion newSuggestion);
}