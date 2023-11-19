package com.example.dataservice.admin;

import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastore.operator.IDataStoreRetrivalOperation;
import com.example.datastructure.Camp;

public interface IAdminCampDBService {
    IDataStoreEditOperation<Camp> DSCreateCamp(Camp camp);
    IDataStoreEditOperation<Camp> DSDeleteCamp(Camp camp);
    IDataStoreEditOperation<Camp> DSEditCamp(Camp camp);
    IDataStoreRetrivalOperation<Camp> DSCampRetrival();
    IDataStoreRetrivalOperation<Camp> DSRelevantCampRetrival();
}
