package com.example.datastructure;

import java.util.ArrayList;

import com.example.UtilityPackage.Pair;
import com.example.dataservice.*;
import com.example.datastore.IDataStoreObject;

public class Staff extends User implements IDataStoreObject<Staff>{

	private StaffDBService dbService;

	/**
	 * Constructor for Staff class, password will be set to default.
	 * @param userId		Unique Identifier of the staff.
	 * @param name			Name of the staff.
	 * @param faculty		Faculty the staff is from.
	 */
	public Staff(String userId, String name, GroupName faculty) {
		super(userId, name, faculty);
		this.dbService = new StaffDBService();
	}


	/**
	 * Constructor for Staff class.
	 * @param userId		Unique Identifier of the staff.
	 * @param name			Name of the staff.
	 * @param faculty		Faculty the user is staff.
	 * @param password		Password of the staff.
	 */
	public Staff(String userId, String name, GroupName faculty, String password) {
		super(userId, name, faculty, password);
		this.dbService = new StaffDBService();
	}

	public Staff(ArrayList<Pair<String, String>> attrMapping){
		super(attrMapping);
	}

	/**
	 * Get method for dbService.
	 * @return		dbService.
	 */
	public StaffDBService getDbService() {
		return this.dbService;
	}

	/**
	 * This method returns a copy of the staff.
	 * @return		A copy of the staff.
	 */
	@Override
	public Staff copyOf() {
		return new Staff(this.getUserId(), this.getName(), this.getFaculty(), this.getPassword());
	}

	
	/**
	 * Returns true if o is equal to the user. userId is used for the comparison.
	 * @param o		other user to compare to.
	 * @return		true if o is equal to the user.
	 */
	@Override
	public boolean isEquals(Staff o) {
		return super.isEquals(o);
	}

}