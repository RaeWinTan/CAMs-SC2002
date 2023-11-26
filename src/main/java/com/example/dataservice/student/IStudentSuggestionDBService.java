package com.example.dataservice.student;

import com.example.datastore.DataStore;
import com.example.datastore.IDataStoreEditable;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;

/**
 * Interface for Student's access to Suggestion data.
 */
public interface IStudentSuggestionDBService {
    public IDataStoreEditOperation<Camp> DSSuggestionCreate(Suggestion suggestion, DataStore<Student> studentDS);
    public IDataStoreEditOperation<Camp> DSSuggestioNDelete(Suggestion suggestion, IDataStoreEditable<Student> studentDSEditable);
    public IDataStoreEditOperation<Camp> DSSuggestionEdit(Suggestion newSuggestion);
}
