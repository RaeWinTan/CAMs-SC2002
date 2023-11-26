package com.example.datastore;

import com.example.datastore.operator.IDataStoreEditOperation;

/**
 * Interface for a DataStore that can be edited.
 */
public interface IDataStoreEditable<T extends IDataStoreObject<T>> {
    public void manageData(IDataStoreEditOperation<T> operation);
}
