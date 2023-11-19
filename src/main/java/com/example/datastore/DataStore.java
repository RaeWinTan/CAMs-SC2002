package com.example.datastore;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.example.datastore.operator.DataStoreEditOperation;
import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.exception.IllegalOperationException;

public class DataStore<T extends IDataStoreObject<T>> implements DataStoreEditable<T>, DataStoreRetrivable<T>{

	private ArrayList<T> data;

	public DataStore(){
		this.data = new ArrayList<T>();
	}

	/**
	 * @param operation Modification operation that will be performed on the datastore.
	 * @see DataStoreEditOperation
	 */
	public void manageData(DataStoreEditOperation<T> operation) throws IllegalOperationException {
		operation.perform(this.data);
	}

	/**
	 * 
	 * @param operation: Retrival operation that will be performed on the datastore.
	 * @see IDataStoreRetrival
	 */
	// TODO: ADD INSTANCE OF
	public ArrayList<T> retrieveData(DataStoreRetrivalOperation<T> operation) throws IllegalOperationException{
		ArrayList<T> copy = (ArrayList<T>) data
							.stream()
							.map(i->i.copyOf())
							.collect((Collectors.toList()));
		return operation.perform(copy);
	}
}