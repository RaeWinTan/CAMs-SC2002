package com.example.DataServicePackage;

import com.example.DataStoreOperatorPackage.ModifyOperator.UserDataStoreChangePassword;
import com.example.DataStructurePackage.User;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;

public abstract class UserDBService<T extends User> implements IUserDataServicable<T>{

    /**
     * This method reutrns a datastore operation to edit an existing user.
     * @param user  User with updated password.
     */
    public IMonoListDataStoreEditOperation<T> DBEditUser(T user){
        return new UserDataStoreChangePassword<T>(user);
    }
}
