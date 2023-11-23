package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.*;

/**
 * Camp DataStore retrival operator to retrieve camps.
 * @see IDataStoreRetrivalOperation
 */
public class StaffCampRetrival implements IDataStoreRetrivalOperation<Camp> {

	private Staff staff;

	/**
	 * Constructor for StaffCampRetrival -> All camps
	 */
	public StaffCampRetrival(){
		this.staff = null;
	}

	/**
	 * Construcfor for StaffCAmpRetrival -> Camps created by the Staff themselves.
	 * @param staff		Staff retrieving camp.
	 */
	public StaffCampRetrival(Staff staff){
		this.staff = staff;
	}

	/**
	 * This method returns all Camps from Camp DataStore if user has not been defined.
	 * If user is specified, filter by if the camp was created by the staff.
	 * @param data	Deep clone of ArrayList of Camps from Camp DataStore.
	 * @return 		ArrayList of Camps
	 */
	public ArrayList<Camp> perform(ArrayList<Camp> data) {
		// Return all campo
		if (this.staff == null)
			return data;

		// Return camps created by staff.
		ArrayList<Camp> relevantCamps = new ArrayList<Camp>();
		for (Camp camp : data) {
			if (camp.getCreatedBy().isEquals(this.staff))
				relevantCamps.add(camp);
		}
		return relevantCamps;
	}
}