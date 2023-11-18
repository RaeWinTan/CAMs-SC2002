package com.example.datastore.bilist.operator;

import java.util.ArrayList;

public interface IBiListDataStoreRetrivalOperation<T> {
    ArrayList<T> perform(ArrayList<T> data1, ArrayList<T> data2);
}
