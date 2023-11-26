package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.objectchecker.CampChecker;
import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.exception.IllegalOperationException;
import com.example.exception.InsufficientPermissionException;
import com.example.exception.ObjectClash;
import com.example.exception.ObjectNotFoundException;

/**
 * Camp DataStore edit operator for editing a camp.
 * @see IDataStoreEditOperation
 */
public class StaffCampEdit implements IDataStoreEditOperation<Camp>{

    private Staff staff;
    private Camp newCamp;

    /**
     * Constructor for StaffCampEdit.
     * @param staff     Staff editing the camp.
     * @param newCamp   Camp with new camp details.
     */
    public StaffCampEdit(Staff staff, Camp newCamp){
        this.staff = staff;
        this.newCamp = newCamp;
    }

    /**
     * Search for camp and set it's values to that in newCamp.
     * @param data  ArrayList of Camps from Camp DataStore.
     */
    @Override
    public void perform(ArrayList<Camp> data) {
        //must check for name clash
        for(Camp c:data){
            if(this.newCamp.getCampName().equals(c.getCampName()) && !this.newCamp.isEquals(c)) throw new ObjectClash("Camp", this.newCamp.getCampName());
        }
        //check that the data camp attributes make sense
        new CampChecker(newCamp);
        // Get camp
        for (Camp camp : data) {
            if (camp.isEquals(this.newCamp)){
                // check staff has permission to edit this camp
                if (!camp.getCreatedBy().isEquals(this.staff))
                    throw new InsufficientPermissionException("Staff editing the camp does not match createdBy in camp.");
                // special checks when camp has more than one participants
                if (camp.getAttendees().size() + camp.getCommittees().size() > 0){
                    if (this.newCamp.getVisibility() != camp.getVisibility() && !this.newCamp.getVisibility())
                         throw new IllegalOperationException("Unable to disable camp when camp has participants.");
                    if (this.newCamp.getUserGroup() != camp.getUserGroup())
                        throw new IllegalOperationException("Unable to change camp user group when camp has participants.");
                }
                camp.setAll(newCamp);
                return;
            }
        }
        throw new ObjectNotFoundException("Camp", "DataStore");
    }
    
}
