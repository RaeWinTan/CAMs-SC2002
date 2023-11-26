package com.example.datastore.operator;

import java.util.ArrayList;

/**
 * IDataStoreRetrivalOperation retrieves data from a given DataStore.
 * Data requried for retrival shall be passed in during initialisation.
 * This operation is to be passed to IDataStoreRetrivable's retriveData() method,
 * where the perform() method of this class will be called.
 */
public interface IDataStoreRetrivalOperation<T> {
	/**
	 * This method is to be called from a DataStore object. 
	 * This method filters the data provided by DataStore and returns a deep clone of the filtered data.
	 * Filter shall differ for each class.
	 * @param data		Deep clone of ArrayList from DataStore.
	 */
	ArrayList<T> perform(ArrayList<T> data);
}