package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.*;

public class StaffCampRetrival implements DataStoreRetrivalOperation<Camp> {

	private Staff staff;

	public StaffCampRetrival(){
		this.staff = null;
	}

	public StaffCampRetrival(Staff staff){
		this.staff = staff;
	}


	/**
	 * This method returns a deep clone of all Camps from Camp DataStore.
	 * @param data	Clone of ArrayList of Camps from Camp DataStore.
	 * @return 		Deep clone of all Camps from DataStore
	 * @see IDstaStoreRetrivable
	 */
	public ArrayList<Camp> perform(ArrayList<Camp> data) {
		
		if (this.staff == null){
			return data;
		}
		
		ArrayList<Camp> relevantCamps = new ArrayList<Camp>();

		for (Camp camp : data) {
			if (camp.getCreatedBy().isEquals(this.staff)){
				relevantCamps.add(camp);
			}
		}

		return relevantCamps;
	}

}