package com.example.datastore.bilist;

import com.example.datastore.bilist.operator.IBiListDataStoreEditOperation;
import com.example.exception.IllegalOperationException;

public interface IBiListDataStoreEditable<T> {
    public void manageData(IBiListDataStoreEditOperation<T> operation) throws IllegalOperationException;
}
