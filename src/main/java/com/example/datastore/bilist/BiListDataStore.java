package com.example.datastore.bilist;

import java.util.ArrayList;

import com.example.datastore.IDataStoreObject;
import com.example.datastore.bilist.operator.IBiListDataStoreEditOperation;
import com.example.datastore.bilist.operator.IBiListDataStoreRetrivalOperation;
import com.example.exception.IllegalOperationException;

public class BiListDataStore<T extends IDataStoreObject<T>> implements IBiListDataStoreEditable<T>, IBiListDataStoreRetrivable<T> {
    private ArrayList<T> list1;
    private ArrayList<T> list2;

    public BiListDataStore(){
        this.list1 = new ArrayList<T>();
        this.list2 = new ArrayList<T>();
    }

    @Override
    public void manageData(IBiListDataStoreEditOperation<T> operation) throws IllegalOperationException{
        operation.perform(list1, list2);
    }

    @Override
    public ArrayList<T> retrieveData(IBiListDataStoreRetrivalOperation<T> operation) throws IllegalOperationException{
        return operation.perform(list2, list1);
    }

}
