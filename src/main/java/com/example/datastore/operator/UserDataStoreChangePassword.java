package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.User;
import com.example.exception.ObjectNotFoundException;

public class UserDataStoreChangePassword<T extends User> implements IDataStoreEditOperation<T> {

    T user;

    public UserDataStoreChangePassword(T user){
        this.user = user;
    }

    @Override
    public void perform(ArrayList<T> data){
        for (int i=0; i<data.size(); i++){
            T u = data.get(i);
            if (u.isEquals(user)){
                u.setPassword(user.getPassword());
                return;
            }
        }
        throw new ObjectNotFoundException("User", "DataStore");
    }
}
