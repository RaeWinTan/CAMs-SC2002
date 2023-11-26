package com.example.dataservice;

import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastructure.User;

/**
 * Interface for User's access to User data.
 */
public interface IUserDataServicable<T extends User> {
    public IDataStoreEditOperation<T> DBUserChangePassword(T user);
}
