package com.example.dataservice;

import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastructure.User;

public interface IUserDataServicable<T extends User> {
    public IDataStoreEditOperation<T> DBUserChangePassword(T user);
}
