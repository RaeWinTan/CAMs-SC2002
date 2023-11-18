package com.example.datastore.bilist;

import java.util.ArrayList;

import com.example.ExceptionPackage.IllegalOperationException;
import com.example.datastore.bilist.operator.IBiListDataStoreRetrivalOperation;

public interface IBiListDataStoreRetrivable<T> {
    public ArrayList<T> retrieveData(IBiListDataStoreRetrivalOperation<T> operation) throws IllegalOperationException;
}
