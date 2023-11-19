package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreObject;
import com.example.exception.IllegalOperationException;

public class DataStoreCreate<T extends IDataStoreObject<T>> implements IDataStoreEditOperation<T> {

	private T obj;

	/**
	 * Constructor for CampDataStoreCreate class.
	 * @param obj: Camp to be created.
	 */
	public DataStoreCreate(T obj) {
		this.obj = obj;
	}

	/**
	 * This method iterates through data to check for duplicates.
	 * If no duplicates, obj will be added to data.
	 * @param data		ArrayList of T from DataStore.
	 * @see IDataStoreEditOperation
	 */
	@Override
	public void perform(ArrayList<T> data) {
		if(data.stream().anyMatch(i->i.isEquals(this.obj))) throw new IllegalOperationException("Object already exists");
		data.add(obj);
	}
}