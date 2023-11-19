package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreObject;
import com.example.exception.IllegalOperationException;

public class DataStoreDelete<T extends IDataStoreObject<T>> implements DataStoreEditOperation<T> {

	private T obj;

	/**
	 * Constructor for CampDataStoreDelete class.
	 * @param obj: Camp to be deleted.
	 */
	public DataStoreDelete(T obj) {
		this.obj = obj;
	}

	/**
	 * This method iterates through data to search for obj.
	 * If found, obj will be removed from data.
	 * @param data		ArrayList of T from DataStore.
	 * @see DataStoreEditOperation
	 */
	@Override
	public void perform(ArrayList<T> data) throws IllegalOperationException {
		if(!data.removeIf(i->i.isEquals(this.obj)))
			throw new IllegalOperationException("Object doesn't exists");
	}
}