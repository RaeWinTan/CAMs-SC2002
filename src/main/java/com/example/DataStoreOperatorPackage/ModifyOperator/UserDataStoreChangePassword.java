package com.example.DataStoreOperatorPackage.ModifyOperator;

import java.util.ArrayList;

import com.example.DataStructurePackage.User;
import com.example.ExceptionPackage.IllegalOperationException;
import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;

public class UserDataStoreChangePassword<T extends User> implements IMonoListDataStoreEditOperation<T> {

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
        // Should not happen, but throw an error if no user in datastore matches the user being edited.
        throw new IllegalOperationException("User not found");
    }
}
