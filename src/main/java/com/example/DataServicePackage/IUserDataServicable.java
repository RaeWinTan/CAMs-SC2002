package com.example.DataServicePackage;

import com.example.DataStructurePackage.User;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;

public interface IUserDataServicable<T extends User> {
    public IMonoListDataStoreEditOperation<T> DBEditUser(T user);
}
