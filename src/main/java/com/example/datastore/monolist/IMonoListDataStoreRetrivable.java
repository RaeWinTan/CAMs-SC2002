package com.example.datastore.monolist;

import java.util.ArrayList;

import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.exception.IllegalOperationException;

public interface IMonoListDataStoreRetrivable<T> {
    public ArrayList<T> retrieveData(IMonoListDataStoreRetrivalOperation<T> operation) throws IllegalOperationException;
}
