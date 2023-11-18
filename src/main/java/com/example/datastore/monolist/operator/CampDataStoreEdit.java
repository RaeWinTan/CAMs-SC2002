package com.example.datastore.monolist.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.exception.IllegalOperationException;

public class CampDataStoreEdit implements IMonoListDataStoreEditOperation<Camp> {

    private Camp newCamp;

    /**
     * Constructor for CampDataStoreEdit class.
     * @param currentCamp       Camp to be edited.
     * @param newCamp           Camp with edited attributes.
     */
    public CampDataStoreEdit(Camp newCamp){
        this.newCamp = newCamp;
    }

	/**
	 * This method iterates through data to search for currentCamp.
	 * If found, the camp attributes will be updated to that of newCamp.
	 * @param data      ArrayList of Camps from Camp DataStore.
     * @see IMonoListDataStoreEditOperation
	 */
    @Override
    public void perform(ArrayList<Camp> data) throws IllegalOperationException {
        for (int i=0; i<data.size(); i++){
            Camp camp = data.get(i);
            if (camp.isEquals(newCamp)){
                camp.setAll(newCamp);
                return;
            }
        }
        // Should not happen, but throw an error if no camps in datastore matches the camp being edited.
        throw new IllegalOperationException("Camp not found");
    }
}
