//TODO: Delete if not being used.

// package com.example.datastore.operator;

// import java.util.ArrayList;

// import com.example.datastore.IDataStoreObject;
// import com.example.exception.IllegalOperationException;

// public class DataStoreDelete<T extends IDataStoreObject<T>> implements IDataStoreEditOperation<T> {

// 	private T obj;

// 	/**
// 	 * Constructor for CampDataStoreDelete class.
// 	 * @param obj: Camp to be deleted.
// 	 */
// 	public DataStoreDelete(T obj) {
// 		this.obj = obj;
// 	}

// 	/**
// 	 * This method iterates through data to search for obj.
// 	 * If found, obj will be removed from data.
// 	 * @param data		ArrayList of T from DataStore.
// 	 * @see IDataStoreEditOperation
// 	 */
// 	@Override
// 	public void perform(ArrayList<T> data) {
// 		if(!data.removeIf(i->i.isEquals(this.obj)))
// 			throw new IllegalOperationException("Object doesn't exists");
// 	}
// }