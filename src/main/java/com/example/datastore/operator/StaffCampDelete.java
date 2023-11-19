package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.exception.IllegalOperationException;
import com.example.exception.InsufficientPermissionException;
import com.example.exception.ObjectNotFoundException;

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
        // Get camp
        for (Camp camp : data) {
            if (camp.isEquals(this.camp)){
                // Check credentials
                if (!camp.getCreatedBy().isEquals(this.staff))
                    throw new InsufficientPermissionException("Staff making deleting camp does not match createdBy in camp.");


                // Deny deletion of camp has participants
                if (camp.getAttendees().size() + camp.getCommittees().size() > 0)
                    throw new IllegalOperationException("Unable to delete camp with participants");
                
                break;
            }
        }

        // Delete camp from campdatastore
        if (data.removeIf(camp->camp.isEquals(this.camp))){
            // Delete camp from staff in staffdatastore
            staffDataStore.manageData(new StaffRemoveCamp(this.staff, this.camp));
            return;
        }

        throw new ObjectNotFoundException("Camp");
    }
    
}
