package com.example.datastore;

import java.util.ArrayList;

import com.example.datastore.operator.IDataStoreRetrivalOperation;

/**
 * Interface for a DataStore that can be retrieved from.
 */
public interface IDataStoreRetrivable<T extends IDataStoreObject<T>> {
    public ArrayList<T> retrieveData(IDataStoreRetrivalOperation<T> operation);
}
