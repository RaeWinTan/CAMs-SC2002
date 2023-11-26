package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.exception.IllegalOperationException;
import com.example.exception.InsufficientPermissionException;
import com.example.exception.ObjectNotFoundException;

/**
 * Camp DataStore edit operator for deleting a camp.
 * @see IDataStoreEditOperation
 */
public class StaffCampDelete implements IDataStoreEditOperation<Camp>{

    Staff staff;
    Camp camp;
    IDataStoreEditable<Staff> staffDataStore;
    IDataStoreEditable<Student> studentDataStore;

    /**
     * Constructor for StaffCampDelete.
     * @param staff             Staff executing the operator.
     * @param camp              Camp to be deleted.
     * @param staffDataStore    Staff DataStore, required to remove camp from original Staff object.
     * @param studentDataStore  Student DataStore, required to remove camp from original Student object.
     */
    public StaffCampDelete(Staff staff, Camp camp, IDataStoreEditable<Staff> staffDataStore, IDataStoreEditable<Student> studentDataStore){
        this.staff = staff;
        this.camp = camp;
        this.staffDataStore = staffDataStore;
        this.studentDataStore = studentDataStore;
    }

    /**
     * Remove Camp from DataStore.
     * Call Staff DataStore to remove camp from staff using StaffRemoveCamp.
     * @param data  ArrayList of Camps from Camp DataStore.
     * @see StaffRemoveCamp
     */
    @Override
    public void perform(ArrayList<Camp> data) {
        // Get camp
        for (Camp camp : data) {
            if (camp.isEquals(this.camp)){
                // Check credentials
                if (!camp.getCreatedBy().isEquals(this.staff))
                    throw new InsufficientPermissionException("Staff deleting camp does not match createdBy in camp.");

                // Deny deletion of camp has participants
                if (camp.getAttendees().size() + camp.getCommittees().size() > 0)
                    throw new IllegalOperationException("Unable to delete camp with participants");
                
                // Remove camps from students
                for (CampMember cm : camp.getAttendees()) {
                    studentDataStore.manageData(new AttendeeRemoveCamp(cm.getStudent(),camp));
                }
                for (CampMember cm : camp.getCommittees()){
                    studentDataStore.manageData(new CommitteeRemoveCamp(cm.getStudent(),camp));
                }

                break;
            }
        }

        

        // Delete camp from campdatastore
        if (data.removeIf(camp->camp.isEquals(this.camp))){
            // Delete camp from staff in staffdatastore
            staffDataStore.manageData(new StaffRemoveCamp(this.staff, this.camp));
            return;
        }

        throw new ObjectNotFoundException("Camp", "DataStore");
    }
    
}
