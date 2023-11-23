package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.User;
import com.example.exception.ObjectNotFoundException;

/**
 * User DataStore edit operator for changing password for a user.
 * @see IDataStoreEditOperation
 */
public class UserDataStoreChangePassword<T extends User> implements IDataStoreEditOperation<T> {

    T user;

    /**
     * Constructor for UserDataStoreChangePassword.
     * @param user  User to change password for, password for this User object will be used to update the password of the actual User.
     */
    public UserDataStoreChangePassword(T user){
        this.user = user;
    }

    /**
     * Search for user and set new password.
     * @param data  ArrayList of User from User DataStore.
     */
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
