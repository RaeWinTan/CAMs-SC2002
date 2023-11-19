package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.exception.IllegalOperationException;

public class StaffAddCamp implements IDataStoreEditOperation<Staff> {

    private Staff staff;
    private Camp camp;
    public StaffAddCamp(Staff staff, Camp camp){
        this.staff = staff;
        this.camp = camp;
    }

    @Override
    public void perform(ArrayList<Staff> data) {
        for (Staff staff : data) {
            if (staff.isEquals(this.staff)){
                staff.getCampsCreated().add(this.camp);
                return;
            }
        }

        throw new IllegalOperationException("Staff not found");
    }
    
}
