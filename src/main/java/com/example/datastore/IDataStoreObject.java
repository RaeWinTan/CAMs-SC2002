package com.example.datastore;

/**
 * Objects that are stored in DataStore must implement this interface so that data retrieved from the DataStore cannot be changed outside.
 */
public interface IDataStoreObject<T> {
    public boolean isEquals(T other);
    public T copyOf();
}
