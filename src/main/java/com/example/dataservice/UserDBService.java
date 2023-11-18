package com.example.dataservice;

import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.UserDataStoreChangePassword;
import com.example.datastructure.User;

public abstract class UserDBService<T extends User> implements IUserDataServicable<T>{

    /**
     * This method reutrns a datastore operation to edit an existing user.
     * @param user  User with updated password.
     */
    public IMonoListDataStoreEditOperation<T> DBEditUser(T user){
        return new UserDataStoreChangePassword<T>(user);
    }
}
