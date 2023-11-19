package com.example.datastore;

import java.util.ArrayList;

import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.exception.IllegalOperationException;

public interface DataStoreRetrivable<T> {
    public ArrayList<T> retrieveData(DataStoreRetrivalOperation<T> operation) throws IllegalOperationException;
}
