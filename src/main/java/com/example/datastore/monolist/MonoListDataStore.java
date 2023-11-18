package com.example.datastore.monolist;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.example.datastore.IDataStoreObject;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.exception.IllegalOperationException;

public class MonoListDataStore<T extends IDataStoreObject<T>> implements IMonoListDataStoreEditable<T>, IMonoListDataStoreRetrivable<T>{

	private ArrayList<T> data;

	public MonoListDataStore(){
		this.data = new ArrayList<T>();
	}

	/**
	 * @param operation Modification operation that will be performed on the datastore.
	 * @see IMonoListDataStoreEditOperation
	 */
	public void manageData(IMonoListDataStoreEditOperation<T> operation) throws IllegalOperationException {
		operation.perform(this.data);
	}

	/**
	 * 
	 * @param operation: Retrival operation that will be performed on the datastore.
	 * @see IDataStoreRetrival
	 */
	// TODO: ADD INSTANCE OF
	public ArrayList<T> retrieveData(IMonoListDataStoreRetrivalOperation<T> operation) throws IllegalOperationException{
		ArrayList<T> copy = (ArrayList<T>) data
							.stream()
							.map(i->i.copyOf())
							.collect((Collectors.toList()));
		return operation.perform(copy);
	}
}