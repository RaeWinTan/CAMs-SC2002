package com.example.datastore;

import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.exception.IllegalOperationException;

public interface IDataStoreEditable<T> {
    public void manageData(IDataStoreEditOperation<T> operation) throws IllegalOperationException;
}
