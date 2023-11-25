package com.example.dataservice.student;

import com.example.datastore.DataStore;
import com.example.datastore.IDataStoreEditable;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastore.operator.IDataStoreRetrivalOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;

public interface IStudentCampDBService {
    public IDataStoreRetrivalOperation<Camp> DSCampRetrival();

    public IDataStoreEditOperation<Camp> DSJoinCampAsAttendee(Camp camp, DataStore<Student> studentDS);

    public IDataStoreEditOperation<Camp> DSJoinCampAsCommittee(Camp camp,  DataStore<Student> studentDS);

    public IDataStoreEditOperation<Camp> DSQuitCampAsAttendee(Camp camp,  IDataStoreEditable<Student> studentDSEditable);
}
