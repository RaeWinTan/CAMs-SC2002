package com.example.datastore.bilist;

import java.util.ArrayList;

import com.example.datastore.bilist.operator.IBiListDataStoreRetrivalOperation;
import com.example.exception.IllegalOperationException;

public interface IBiListDataStoreRetrivable<T> {
    public ArrayList<T> retrieveData(IBiListDataStoreRetrivalOperation<T> operation) throws IllegalOperationException;
}
