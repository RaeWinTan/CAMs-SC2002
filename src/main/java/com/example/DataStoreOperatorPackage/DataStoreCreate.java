package com.example.DataStoreOperatorPackage;

import java.util.ArrayList;

import com.example.ExceptionPackage.IllegalOperationException;
import com.example.datastore.IDataStoreObject;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;

public class DataStoreCreate<T extends IDataStoreObject<T>> implements IMonoListDataStoreEditOperation<T> {

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
	 * @see IMonoListDataStoreEditOperation
	 */
	@Override
	public void perform(ArrayList<T> data) throws IllegalOperationException{
		if(data.stream().anyMatch(i->i.isEquals(this.obj))) throw new IllegalOperationException("Object already exists");
		data.add(obj);
	}
}