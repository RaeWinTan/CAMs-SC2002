package com.example.datastore.operator;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.example.datastructure.User;
import com.example.exception.InvalidLoginCredentialException;

/**
 * User DataStore retrival operator for logging in.
 * @see IDataStoreRetrivalOperation
 */
public class UserLoginRetrival<T extends User> implements IDataStoreRetrivalOperation<T>{

    private String userId;
    private String password;

    /**
     * Constructor for UserLoginRetrival class.
     * @param userId    Unique identifier of the user.
     * @param password  Password of the user.
     */
    public UserLoginRetrival(String userId, String password){
        this.userId = userId;
        this.password = password;
    }

    /**
     * Checks the login credentials and should return a User ArrayList of size 1,
     * the arrayList should be populated by the User whose userId and password matches the information
     * provided during initialisation.
     * @param data  ArrayList of Users from User DataStore.
     * @return A User ArrayList of size 1.
     * @see IDataStoreRetrival
     */
    public ArrayList<T> perform(ArrayList<T> data){
        ArrayList<T> loggedIn = (ArrayList<T>) data.stream()
        .filter(i->this.filter(i))
        .collect(Collectors.toList());

        if (loggedIn.isEmpty())
            throw new InvalidLoginCredentialException();

        return loggedIn;
    }

    /**
     * This method only returns true if userId and password matches User.
     * @param user User to be evaluated.
     * @return  true if userId and password matches.
     */
    private boolean filter(T user){
        return user.getUserId().equals(this.userId) && user.getPassword().equals(this.password);
    }
}