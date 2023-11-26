package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.exception.ObjectNotFoundException;

/**
 * Staff DataStore edit operator to add a Camp to Staff.
 * NOTE: This class is only to be used in StaffCampCreate.
 * @see CommitteeDeleteSuggestion
 * @see IDataStoreEditOperation
 */
public class StaffAddCamp implements IDataStoreEditOperation<Staff> {

    private Staff staff;
    private Camp camp;

    /**
     * Constructor for StaffAddCamp.
     * @param staff     Staff to add Camp to. 
     * @param camp      Camp to be added.
     */
    public StaffAddCamp(Staff staff, Camp camp){
        this.staff = staff;
        this.camp = camp;
    }

    /**
     * Search for staff and add camp to it.
     * @param data  ArrayList of Staff from Staff DataStore.
     */
    @Override
    public void perform(ArrayList<Staff> data) {
        for (Staff staff : data) {
            if (staff.isEquals(this.staff)){
                staff.getCampsCreated().add(this.camp);
                return;
            }
        }
        throw new ObjectNotFoundException("Staff", "DataStore");
    }
}
