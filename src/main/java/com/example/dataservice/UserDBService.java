package com.example.dataservice;

import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastore.operator.UserDataStoreChangePassword;
import com.example.datastructure.User;

/**
 * Class for creating DataStore operator classes that User has access to.
 * @see IDataStoreEditOperation  
 */
public abstract class UserDBService<T extends User> implements IUserDataServicable<T>{
    public IDataStoreEditOperation<T> DBUserChangePassword(T user){
        return new UserDataStoreChangePassword<T>(user);
    }
}
