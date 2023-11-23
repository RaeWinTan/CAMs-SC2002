package com.example.datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastore.operator.IDataStoreRetrivalOperation;
import com.example.exception.IllegalOperationException;

/**
 * Container for IDataStoreObjects. 
 * Data from DataStoreis retrieved using IDataStoreRetrivalOperations.
 * All data retrieved from the DataStore should be a copy, 
 * ie, any modifications outside the datastore is not persistence.
 * Any modifications must be done through IDataStoreEditOperations.
 * @see IDataStoreRetrivalOperation
 * @see IDataStoreEditOperation
 */
public class DataStore<T extends IDataStoreObject<T>> implements IDataStoreEditable<T>, IDataStoreRetrivable<T>{

	private ArrayList<T> data;

	/**
	 * Constructor for DataStore.
	 */
	public DataStore(){
		this.data = new ArrayList<T>();
	}

	/**
	 * Pass in data to the operation to be edited.
	 * @param operation Modification operation that will be performed on the datastore.
	 * @see IDataStoreEditOperation
	 */
	public void manageData(IDataStoreEditOperation<T> operation) throws IllegalOperationException {
		operation.perform(this.data);
	}

	/**
	 * Pass in a deep copy of data to the operation.
	 * @param operation: Retrival operation that will be performed on the datastore.
	 * @see IDataStoreRetrival
	 */
	public ArrayList<T> retrieveData(IDataStoreRetrivalOperation<T> operation) throws IllegalOperationException{
		List<T> copy =  data
							.stream()
							.map(i->i.copyOf())
							.collect((Collectors.toList()));
		return operation.perform(new ArrayList<T>(copy));
	}
}