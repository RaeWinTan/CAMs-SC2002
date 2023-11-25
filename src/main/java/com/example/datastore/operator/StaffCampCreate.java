package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastore.ObjectChecker.CampChecker;
import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.exception.IllegalOperationException;
import com.example.exception.ObjectClash;

/**
 * Camp DataStore edit operator for creating a new camp
 * @see IDataStoreEditOperation
 */
public class StaffCampCreate implements IDataStoreEditOperation<Camp> {

    private Staff staff;
    private Camp camp;
    private IDataStoreEditable<Staff> staffDataStore;

    /**
     * Constructor for StaffCampCreate
     * @param staff             Staff creating the camp.
     * @param camp              Camp to be created.
     * @param staffDataStore    Staff DataStore, required for adding camp to original Staff object.
     */
    public StaffCampCreate(Staff staff, Camp camp, IDataStoreEditable<Staff> staffDataStore){
        this.staff = staff;
        this.camp = camp;//i think we should do clone here cuz the camp reference is still in the controler
        this.staffDataStore = staffDataStore;
    }

    /**
     * Add camp to DataStore.
     * Call Staff DataStore to add Camp to Staff using StaffAddCamp.
     * @param data
     * @see StaffAddCamp
     */
    @Override
    public void perform(ArrayList<Camp> data) {
        // basic checks
        if (!this.camp.getCreatedBy().isEquals(this.staff)){
            throw new IllegalOperationException("Staff creating camp does not match createdBy in camp");
        }
        for(Camp d:data){//make sure no clash in camp name
            if(d.getCampName().equals( this.camp.getCampName())) throw new ObjectClash("Camp", camp.getCampName());
        }
        //make sure the attributes are logical
        new CampChecker(this.camp);
        
        // Add camp to staff
        staffDataStore.manageData(new StaffAddCamp(this.staff, this.camp));

        // Add camp to datastore
        data.add(camp);
    }
    
}
