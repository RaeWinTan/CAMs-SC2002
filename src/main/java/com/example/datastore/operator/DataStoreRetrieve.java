package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreObject;
import com.example.exception.IllegalOperationException;

public class DataStoreRetrieve<T extends IDataStoreObject<T>> implements IDataStoreRetrivalOperation<T>{

    private T obj;
    public DataStoreRetrieve(T obj){
        this.obj = obj;
    }


    @Override
    public ArrayList<T> perform(ArrayList<T> data) {
        ArrayList<T> rtn = new ArrayList<T>();
        for (T obj : data) {
            if (obj.isEquals(this.obj)){
                rtn.add(obj);
                return rtn;
            }
        }

        throw new IllegalOperationException("Object not found");
    }
    
}
