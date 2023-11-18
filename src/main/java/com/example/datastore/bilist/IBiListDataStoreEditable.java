package com.example.datastore.bilist;

import com.example.ExceptionPackage.IllegalOperationException;
import com.example.datastore.bilist.operator.IBiListDataStoreEditOperation;

public interface IBiListDataStoreEditable<T> {
    public void manageData(IBiListDataStoreEditOperation<T> operation) throws IllegalOperationException;
}
