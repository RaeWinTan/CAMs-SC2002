package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.exception.ObjectNotFoundException;

/**
 * Staff DataStore Operator to remove camp from Staff object.
 * NOTE: This class is only to be used in StaffCampDelete.
 * @see StaffCampDelete
 * @see IDataStoreEditOperation
 */
public class StaffRemoveCamp implements IDataStoreEditOperation<Staff>{

    private Staff staff;
    private Camp camp;

    /**
     * Constructor for StaffRemoveCamp
     * @param staff Staff to remove Camp from.
     * @param camp  Camp to be removed.
     */
    public StaffRemoveCamp(Staff staff, Camp camp){
        this.staff = staff;
        this.camp = camp;
    }

    /**
     * Search for Staff and remove Camp from it.
     * @param data ArrayList of Staff from Staff DataStore.
     */
    @Override
    public void perform(ArrayList<Staff> data) {
        for (Staff staff : data) {
            if (staff.isEquals(this.staff)){
                if (!staff.getCampsCreated().removeIf(camp->camp.isEquals(this.camp))){
                    throw new ObjectNotFoundException("Camp", "Staff");
                }
                return;
            }
        }
        throw new ObjectNotFoundException("Staff", "DataStore");
    }
    
}
