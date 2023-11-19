package com.example.datastore;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastore.operator.IDataStoreRetrivalOperation;
import com.example.exception.IllegalOperationException;

public class DataStore<T extends IDataStoreObject<T>> implements IDataStoreEditable<T>, IDataStoreRetrivable<T>{

	private ArrayList<T> data;

	public DataStore(){
		this.data = new ArrayList<T>();
	}

	/**
	 * @param operation Modification operation that will be performed on the datastore.
	 * @see IDataStoreEditOperation
	 */
	public void manageData(IDataStoreEditOperation<T> operation) throws IllegalOperationException {
		operation.perform(this.data);
	}

	/**
	 * 
	 * @param operation: Retrival operation that will be performed on the datastore.
	 * @see IDataStoreRetrival
	 */
	// TODO: ADD INSTANCE OF
	public ArrayList<T> retrieveData(IDataStoreRetrivalOperation<T> operation) throws IllegalOperationException{
		ArrayList<T> copy = (ArrayList<T>) data
							.stream()
							.map(i->i.copyOf())
							.collect((Collectors.toList()));
		return operation.perform(copy);
	}
}