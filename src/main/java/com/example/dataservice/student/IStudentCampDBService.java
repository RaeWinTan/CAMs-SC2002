package com.example.dataservice.student;

import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.datastructure.Camp;

public interface IStudentCampDBService {
    public DataStoreRetrivalOperation<Camp> DSCampRetrival();
}
