package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.exception.IllegalOperationException;

public class StaffCampCreate implements IDataStoreEditOperation<Camp> {

    private Staff staff;
    private Camp camp;
    private IDataStoreEditable<Staff> staffDataStore;

    public StaffCampCreate(Staff staff, Camp camp, IDataStoreEditable<Staff> staffDataStore){
        this.staff = staff;
        this.camp = camp;
        this.staffDataStore = staffDataStore;
    }

    @Override
    public void perform(ArrayList<Camp> data) {
        // basic checks
        if (!this.camp.getCreatedBy().isEquals(this.staff)){
            throw new IllegalOperationException("Staff creating camp does not match createdBy in camp");
        }
        // Add camp to staff
        staffDataStore.manageData(new StaffAddCamp(this.staff, this.camp));

        // Add camp to datastore
        data.add(camp);
    }
    
}
