package com.example.datastore.monolist;

import com.example.ExceptionPackage.IllegalOperationException;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;

public interface IMonoListDataStoreEditable<T> {
    public void manageData(IMonoListDataStoreEditOperation<T> operation) throws IllegalOperationException;
}
