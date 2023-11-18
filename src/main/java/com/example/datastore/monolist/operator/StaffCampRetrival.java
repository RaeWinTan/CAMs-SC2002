package com.example.datastore.monolist.operator;

import java.util.ArrayList;

import com.example.datastructure.*;

public class StaffCampRetrival implements IMonoListDataStoreRetrivalOperation<Camp> {

	/**
	 * This method returns a deep clone of all Camps from Camp DataStore.
	 * @param data	Clone of ArrayList of Camps from Camp DataStore.
	 * @return 		Deep clone of all Camps from DataStore
	 * @see IDstaStoreRetrivable
	 */
	public ArrayList<Camp> perform(ArrayList<Camp> data) {
		
		return data;
	}

}