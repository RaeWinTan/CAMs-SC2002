package com.example.dataservice.admin;

import com.example.datastore.IDataStoreEditable;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastore.operator.IDataStoreRetrivalOperation;
import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;

/**
 * Interface for Staff's access to Camp data.
 */
public interface IStaffCampDBService {
    IDataStoreEditOperation<Camp> DSCreateCamp(Camp camp, IDataStoreEditable<Staff> staffDataStore);
    IDataStoreEditOperation<Camp> DSDeleteCamp(Camp camp, IDataStoreEditable<Staff> staffDataStore, IDataStoreEditable<Student> studentDataStore);
    IDataStoreEditOperation<Camp> DSEditCamp(Camp camp);
    IDataStoreRetrivalOperation<Camp> DSCampRetrival();
}
