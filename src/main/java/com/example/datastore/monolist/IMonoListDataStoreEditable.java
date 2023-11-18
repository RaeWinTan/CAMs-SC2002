package com.example.datastore.monolist;

import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.exception.IllegalOperationException;

public interface IMonoListDataStoreEditable<T> {
    public void manageData(IMonoListDataStoreEditOperation<T> operation) throws IllegalOperationException;
}
