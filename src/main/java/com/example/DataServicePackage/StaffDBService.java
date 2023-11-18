package com.example.DataServicePackage;

import com.example.DataServicePackage.AdminDataService.IAdminCampDBService;
import com.example.DataStoreOperatorPackage.DataStoreCreate;
import com.example.DataStoreOperatorPackage.DataStoreDelete;
import com.example.DataStoreOperatorPackage.ModifyOperator.CampDataStoreEdit;
import com.example.DataStoreOperatorPackage.RetrivalOperator.StaffCampRetrival;
import com.example.DataStructurePackage.Camp;
import com.example.DataStructurePackage.Staff;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;

public class StaffDBService extends UserDBService<Staff> implements IAdminCampDBService {

	/**
	 * This method returns a datastore operation to create a new camp.
	 * @param camp: Camp to be created.
	 */
	@Override
	public IMonoListDataStoreEditOperation<Camp> DSCreateCamp(Camp camp) {
		return new DataStoreCreate<Camp>(camp);
	}

	/**
	 * This method returns a datastore operation to delete an existing camp.
	 * @param camp: Camp to be deleted.
	 */
	@Override
	public IMonoListDataStoreEditOperation<Camp> DSDeleteCamp(Camp camp) {
		return new DataStoreDelete<Camp>(camp);
	}

	/**
	 * This method returns a datastore operation to edit an existing camp.
	 * @param camp: Camp with updated attributes.
	 */
	@Override
	public IMonoListDataStoreEditOperation<Camp> DSEditCamp(Camp camp) {
		return new CampDataStoreEdit(camp);
	}

	/**
	 * 
	 * This method returns a datastore operation to retrieve camps.
	 */
	@Override
	public IMonoListDataStoreRetrivalOperation<Camp> DSCampRetrival() {
		return new StaffCampRetrival();
	}
}