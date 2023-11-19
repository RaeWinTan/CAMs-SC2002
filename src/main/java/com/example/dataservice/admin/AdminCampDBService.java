package com.example.dataservice.admin;

import com.example.datastore.operator.CampDataStoreEdit;
import com.example.datastore.operator.DataStoreCreate;
import com.example.datastore.operator.DataStoreDelete;
import com.example.datastore.operator.DataStoreEditOperation;
import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.datastore.operator.StaffCampRetrival;
import com.example.datastructure.Camp;
import com.example.datastructure.Staff;

public class AdminCampDBService implements IAdminCampDBService {


	private Staff staff;
	public AdminCampDBService(Staff staff){
		this.staff = staff;
	}


    /**
	 * This method returns a datastore operation to create a new camp.
	 * @param camp: Camp to be created.
	 */
	@Override
	public DataStoreEditOperation<Camp> DSCreateCamp(Camp camp) {
		return new DataStoreCreate<Camp>(camp);
	}

	/**
	 * This method returns a datastore operation to delete an existing camp.
	 * @param camp: Camp to be deleted.
	 */
	@Override
	public DataStoreEditOperation<Camp> DSDeleteCamp(Camp camp) {
		return new DataStoreDelete<Camp>(camp);
	}

	/**
	 * This method returns a datastore operation to edit an existing camp.
	 * @param camp: Camp with updated attributes.
	 */
	@Override
	public DataStoreEditOperation<Camp> DSEditCamp(Camp camp) {
		return new CampDataStoreEdit(camp);
	}

	/**
	 * 
	 * This method returns a datastore operation to retrieve camps.
	 */
	@Override
	public DataStoreRetrivalOperation<Camp> DSCampRetrival() {
		return new StaffCampRetrival();
	}

	/**
	 * 
	 * This method returns a datastore operation to retrieve relevant camps.
	 */
	@Override
	public DataStoreRetrivalOperation<Camp> DSRelevantCampRetrival() {
		return new StaffCampRetrival(this.staff);
	}
}
