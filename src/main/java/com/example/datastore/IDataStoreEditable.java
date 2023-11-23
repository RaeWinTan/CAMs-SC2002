package com.example.datastore;

import com.example.datastore.operator.IDataStoreEditOperation;

public interface IDataStoreEditable<T extends IDataStoreObject<T>> {
    public void manageData(IDataStoreEditOperation<T> operation);
}
