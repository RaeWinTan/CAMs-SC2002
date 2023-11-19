package com.example.dataservice.admin;

import com.example.datastore.operator.DataStoreEditOperation;
import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.datastructure.Camp;

public interface IAdminCampDBService {
    DataStoreEditOperation<Camp> DSCreateCamp(Camp camp);
    DataStoreEditOperation<Camp> DSDeleteCamp(Camp camp);
    DataStoreEditOperation<Camp> DSEditCamp(Camp camp);
    DataStoreRetrivalOperation<Camp> DSCampRetrival();
    DataStoreRetrivalOperation<Camp> DSRelevantCampRetrival();
}
