package com.example.datastore.operator;

import java.util.ArrayList;

/**
 * IDataStoreEditoperation modifies data inside a given DataStore.
 * Data requried for modification shall be passed in during initialisation.
 * This operation is to be passed to IDataStoreEditable's mangadeData() method,
 * where the perform() method of this class will be called.
 */
public interface IDataStoreEditOperation<T> {
	/**
	 * This method is to be called from a DataStore object. 
	 * This method modifies data provided by DataStore. 
	 * Modification behaviour shall differ for each class.
	 * @param data		ArrayList from DataStore.
	 * @see DataStore
	 */
	void perform(ArrayList<T> data);
}