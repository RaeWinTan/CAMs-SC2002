package com.example.datastore;

/**
 * Objects that are stored in DataStore must implement this interface.
 */
public interface IDataStoreObject<T> {
    public boolean isEquals(T other);
    public T copyOf();
}
