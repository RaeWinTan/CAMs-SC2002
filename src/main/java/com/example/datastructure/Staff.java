package com.example.datastructure;

import java.util.ArrayList;

import com.example.datastore.IDataStoreObject;
import com.example.utility.Pair;

public class Staff extends User implements IDataStoreObject<Staff>{

	/**
	 * Constructor for Staff class, password will be set to default.
	 * @param userId		Unique Identifier of the staff.
	 * @param name			Name of the staff.
	 * @param faculty		Faculty the staff is from.
	 */
	public Staff(String userId, String name, GroupName faculty) {
		super(userId, name, faculty);
	}


	/**
	 * Constructor for Staff class.
	 * @param userId		Unique Identifier of the staff.
	 * @param name			Name of the staff.
	 * @param faculty		Faculty the user is staff.
	 * @param password		Password of the staff.
	 */
	private Staff(String userId, String name, GroupName faculty, String password) {
		super(userId, name, faculty, password);
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