package com.example.DataServicePackage.AdminDataService;

import com.example.DataStructurePackage.Camp;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;

public interface IAdminCampDBService {
    IMonoListDataStoreEditOperation<Camp> DSCreateCamp(Camp camp);
    IMonoListDataStoreEditOperation<Camp> DSDeleteCamp(Camp camp);
    IMonoListDataStoreEditOperation<Camp> DSEditCamp(Camp camp);
    IMonoListDataStoreRetrivalOperation<Camp> DSCampRetrival();
}
