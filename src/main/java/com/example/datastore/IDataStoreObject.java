package com.example.datastore;

public interface IDataStoreObject<T> {
    public boolean isEquals(T other);
    public T copyOf();
}
