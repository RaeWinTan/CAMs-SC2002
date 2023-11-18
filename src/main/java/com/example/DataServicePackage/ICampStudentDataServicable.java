package com.example.DataServicePackage;

import com.example.DataStructurePackage.Camp;
import com.example.DataStructurePackage.Student;
import com.example.UtilityPackage.DataStorePair;
import com.example.datastore.bilist.BiListDataStore;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;

public interface ICampStudentDataServicable {
    public IMonoListDataStoreRetrivalOperation<Camp> DSCampRetrival(BiListDataStore<DataStorePair<Student,Camp>> scDataStore);
}
