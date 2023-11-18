package com.example.DataServicePackage.StudentDataService;

import com.example.DataStructurePackage.Camp;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;

public interface IStudentCampDBService {
    IMonoListDataStoreRetrivalOperation<Camp> DSCampRetrival();
}
