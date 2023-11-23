package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreObject;
import com.example.exception.ObjectNotFoundException;

/**
 * Any DataStore retrival operator to retrieve the latest copy of the given object.
 */
public class DataStoreRetrieve<T extends IDataStoreObject<T>> implements IDataStoreRetrivalOperation<T>{

    private T obj;

    /**
     * Constructor for DataStoreRetrieve.
     * @param obj   Object to be retrieved.
     */
    public DataStoreRetrieve(T obj){
        this.obj = obj;
    }

    /**
     * Search for object and return it.
     * @param data Deep clone of ArrayList of DataStoreObjects from DataStoreObjects DataStore.
     * @return  ArrayList of size 1 containing the latest copy of specified object.
     */
    @Override
    public ArrayList<T> perform(ArrayList<T> data) {
        ArrayList<T> rtn = new ArrayList<T>();
        for (T obj : data) {
            if (obj.isEquals(this.obj)){
                rtn.add(obj);
                return rtn;
            }
        }

        throw new ObjectNotFoundException("Object", "DataStore");
    }
}
