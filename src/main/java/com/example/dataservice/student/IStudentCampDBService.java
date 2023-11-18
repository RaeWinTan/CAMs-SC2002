package com.example.dataservice.student;

import com.example.UtilityPackage.DataStorePair;
import com.example.datastore.bilist.BiListDataStore;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;

public interface IStudentCampDBService {
    public IMonoListDataStoreRetrivalOperation<Camp> DSCampRetrival(BiListDataStore<DataStorePair<Student,Camp>> scDataStore);
}
