package com.example.datastore;

import java.util.ArrayList;

import com.example.datastore.operator.IDataStoreRetrivalOperation;

public interface IDataStoreRetrivable<T extends IDataStoreObject<T>> {
    public ArrayList<T> retrieveData(IDataStoreRetrivalOperation<T> operation);
}
