package com.example.datastore.operator;

import java.util.ArrayList;

public interface IDataStoreRetrivalOperation<T> {

	/**
	 * This method is to be called from a DataStore object. 
	 * Information required should have been provided during intialisation.
	 * This method filters the data provided by DataStore and returns a deep clone of the filtered data.
	 * @param data		ArrayList from DataStore.
	 * @see DataStore
	 */
	ArrayList<T> perform(ArrayList<T> data);

}