package com.example.dataservice.admin;

import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.datastructure.Camp;

public interface IAdminCampDBService {
    IMonoListDataStoreEditOperation<Camp> DSCreateCamp(Camp camp);
    IMonoListDataStoreEditOperation<Camp> DSDeleteCamp(Camp camp);
    IMonoListDataStoreEditOperation<Camp> DSEditCamp(Camp camp);
    IMonoListDataStoreRetrivalOperation<Camp> DSCampRetrival();
}
