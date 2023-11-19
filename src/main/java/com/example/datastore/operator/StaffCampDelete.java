package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.exception.IllegalOperationException;

public class StaffCampDelete implements IDataStoreEditOperation<Camp>{

    Staff staff;
    Camp camp;
    IDataStoreEditable<Staff> staffDataStore;

    public StaffCampDelete(Staff staff, Camp camp, IDataStoreEditable<Staff> staffDataStore){
        this.staff = staff;
        this.camp = camp;
        this.staffDataStore = staffDataStore;
    }

    @Override
    public void perform(ArrayList<Camp> data) {
        if (data.removeIf(camp->camp.isEquals(this.camp))){
            staffDataStore.manageData(new StaffRemoveCamp(this.staff, this.camp));
        }
        throw new IllegalOperationException("Camp not found");
    }
    
}
