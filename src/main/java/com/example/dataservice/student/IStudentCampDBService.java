package com.example.dataservice.student;

import com.example.datastore.bilist.BiListDataStore;
import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.utility.DataStorePair;

public interface IStudentCampDBService {
    public DataStoreRetrivalOperation<Camp> DSCampRetrival(BiListDataStore<DataStorePair<Student,Camp>> scDataStore);
}
