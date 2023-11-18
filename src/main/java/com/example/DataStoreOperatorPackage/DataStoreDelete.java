package com.example.DataStoreOperatorPackage;

import java.util.ArrayList;

import com.example.ExceptionPackage.IllegalOperationException;
import com.example.datastore.IDataStoreObject;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;

public class DataStoreDelete<T extends IDataStoreObject<T>> implements IMonoListDataStoreEditOperation<T> {

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
	 * @see IMonoListDataStoreEditOperation
	 */
	@Override
	public void perform(ArrayList<T> data) throws IllegalOperationException {
		if(!data.removeIf(i->i.isEquals(this.obj)))
			throw new IllegalOperationException("Object doesn't exists");
	}
}