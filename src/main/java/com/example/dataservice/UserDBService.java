package com.example.dataservice;

import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastore.operator.UserDataStoreChangePassword;
import com.example.datastructure.User;

public abstract class UserDBService<T extends User> implements IUserDataServicable<T>{

    /**
     * This method reutrns a datastore operation to edit an existing user.
     * @param user  User with updated password.
     */
    public IDataStoreEditOperation<T> DBUserChangePassword(T user){
        return new UserDataStoreChangePassword<T>(user);
    }
}
