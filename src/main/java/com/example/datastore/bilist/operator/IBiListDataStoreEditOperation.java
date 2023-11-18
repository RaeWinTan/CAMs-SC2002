package com.example.datastore.bilist.operator;

import java.util.ArrayList;

import com.example.exception.IllegalOperationException;

public interface IBiListDataStoreEditOperation<T> {
    public void perform(ArrayList<T> list1, ArrayList<T> list2) throws IllegalOperationException;
}
