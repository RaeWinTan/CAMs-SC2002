package com.example.DataStoreOperatorPackage;

import java.util.ArrayList;

import com.example.DataLoaderPackage.DataLoader;
import com.example.DataStructurePackage.User;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;

public class UserDataStoreLoad<T extends User> implements IMonoListDataStoreEditOperation<T> {
    private DataLoader<T> dataLoader;

    /**
     * Constructor for UserDataStoreLoad class.
     * @param dataloader    Procedure in which data will be loaded.
     */
    public UserDataStoreLoad(DataLoader<T> dataloader){
        this.dataLoader = dataloader;
    }

    /**
     * This method calls the loadData method from dataLoader, which returns an ArrayList of Users.
     * This method then adds these Users to the DataStore.
     * @param data  ArrayList of Users from User DataStore.
     * @see IMonoListDataStoreEditOperation
     */
    public void perform(ArrayList<T> data){
        this.dataLoader.loadData().forEach(
            user->data.add(user)
        );
    }
}
