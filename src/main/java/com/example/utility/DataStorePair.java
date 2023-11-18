package com.example.utility;

import com.example.datastore.IDataStoreObject;

public class DataStorePair<T extends IDataStoreObject<T>, U extends IDataStoreObject<U>> extends Pair<T,U> implements IDataStoreObject<DataStorePair<T,U>> {

    public DataStorePair(T first, U second){
        super(first,second);
    }

    @Override
    public boolean isEquals(DataStorePair<T, U> other) {
        return this.getFirst().equals(other.getFirst()) && this.getSecond().equals(other.getSecond());
    }

    @Override
    public DataStorePair<T, U> copyOf() {
        return new DataStorePair<T,U>(this.getFirst().copyOf(), this.getSecond().copyOf());
    }
    
}
