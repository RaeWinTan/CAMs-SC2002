package com.example.datastore.monolist.operator;

import java.util.ArrayList;

import com.example.ExceptionPackage.IllegalOperationException;

public interface IMonoListDataStoreEditOperation<IDataStoreObject> {

	/**
	 * This method is to be called from a DataStore object. 
	 * Information required should have been provided during intialisation.
	 * This method modifies data provided by DataStore.
	 * @param data		ArrayList from DataStore.
	 * @see DataStore
	 */
	void perform(ArrayList<IDataStoreObject> data) throws IllegalOperationException;

}