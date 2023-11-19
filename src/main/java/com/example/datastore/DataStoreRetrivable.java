package com.example.datastore;

import java.util.ArrayList;

import com.example.datastore.operator.IDataStoreRetrivalOperation;
import com.example.exception.IllegalOperationException;

public interface DataStoreRetrivable<T> {
    public ArrayList<T> retrieveData(IDataStoreRetrivalOperation<T> operation) throws IllegalOperationException;
}
