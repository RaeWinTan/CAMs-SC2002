package com.example.DataStoreOperatorPackage.RetrivalOperator;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.example.DataStructurePackage.*;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;

public class StaffCampRetrival implements IMonoListDataStoreRetrivalOperation<Camp> {

	/**
	 * This method returns a deep clone of all Camps from Camp DataStore.
	 * @param data	Clone of ArrayList of Camps from Camp DataStore.
	 * @return 		Deep clone of all Camps from DataStore
	 * @see IDstaStoreRetrivable
	 */
	public ArrayList<Camp> perform(ArrayList<Camp> data) {
		
		return new ArrayList( data.stream().map(i->i.copyOf()).collect(Collectors.toList()));
	}

}