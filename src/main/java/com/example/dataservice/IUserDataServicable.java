package com.example.dataservice;

import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastructure.User;

public interface IUserDataServicable<T extends User> {
    public IMonoListDataStoreEditOperation<T> DBEditUser(T user);
}
