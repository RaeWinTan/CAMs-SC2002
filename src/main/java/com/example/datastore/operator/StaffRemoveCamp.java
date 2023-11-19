package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.exception.ObjectNotFoundException;

public class StaffRemoveCamp implements IDataStoreEditOperation<Staff>{

    private Staff staff;
    private Camp camp;

    public StaffRemoveCamp(Staff staff, Camp camp){
        this.staff = staff;
        this.camp = camp;
    }

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
