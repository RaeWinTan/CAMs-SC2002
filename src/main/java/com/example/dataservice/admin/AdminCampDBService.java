package com.example.dataservice.admin;

import com.example.datastore.monolist.operator.CampDataStoreEdit;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.datastore.monolist.operator.MonoListDataStoreCreate;
import com.example.datastore.monolist.operator.MonoListDataStoreDelete;
import com.example.datastore.monolist.operator.StaffCampRetrival;
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
	public IMonoListDataStoreEditOperation<Camp> DSCreateCamp(Camp camp) {
		return new MonoListDataStoreCreate<Camp>(camp);
	}

	/**
	 * This method returns a datastore operation to delete an existing camp.
	 * @param camp: Camp to be deleted.
	 */
	@Override
	public IMonoListDataStoreEditOperation<Camp> DSDeleteCamp(Camp camp) {
		return new MonoListDataStoreDelete<Camp>(camp);
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

	/**
	 * 
	 * This method returns a datastore operation to retrieve relevant camps.
	 */
	@Override
	public IMonoListDataStoreRetrivalOperation<Camp> DSRelevantCampRetrival() {
		return new StaffCampRetrival(this.staff);
	}
}
