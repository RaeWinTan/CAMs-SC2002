package com.example.dataservice;

import com.example.datastore.operator.DataStoreEditOperation;
import com.example.datastructure.User;

public interface IUserDataServicable<T extends User> {
    public DataStoreEditOperation<T> DBUserChangePassword(T user);
}
