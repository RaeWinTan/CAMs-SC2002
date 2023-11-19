package com.example.datastore;

import com.example.datastore.operator.DataStoreEditOperation;
import com.example.exception.IllegalOperationException;

public interface DataStoreEditable<T> {
    public void manageData(DataStoreEditOperation<T> operation) throws IllegalOperationException;
}
