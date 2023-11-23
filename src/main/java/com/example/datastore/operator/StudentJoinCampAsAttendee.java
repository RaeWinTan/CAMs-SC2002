package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;
import com.example.exception.IllegalOperationException;
import com.example.exception.ObjectNotFoundException;

/**
 * Camp DataStore edit operator for student to join a camp as an attendee.
 * @see IDataStoreEditOperation
 */
public class StudentJoinCampAsAttendee implements IDataStoreEditOperation<Camp> {

    Student student;
    Camp camp;
    IDataStoreEditable<Student> studenDataStore;

    /**
     * Constructor for StudentJoiNCampAsAttendee
     * @param student           Student joining the Camp
     * @param camp              Camp to be joined.
     * @param studentDataStore  Student DataStore, required to update original Student object's attending list.
     */
    public StudentJoinCampAsAttendee(Student student, Camp camp, IDataStoreEditable<Student> studentDataStore){
        this.student = student;
        this.camp = camp;
        this.studenDataStore = studentDataStore;
    }

    /**
     * Search for Camp and add Student to it's attendee list.
     * Call Student DataStore to add Camp to the student's attending list using StudentAddAttendingCamp.
     * @param data  ArrayList of Camp from Camp DataStore.
     * @see StudentAddAttendingCamp
     */
    @Override
    public void perform(ArrayList<Camp> data) {
        // Check if camp has enough slots
        if (this.camp.getRemaindingSlots() <= 0){
            throw new IllegalOperationException("Camp has reached the maximum number of participants.");
        }

        // TODO: Check for clash in dates

        
        for (Camp camp : data) {
            if (camp.isEquals(this.camp)){
                // Add student to attendee array in camp
                camp.getAttendees().add(new CampMember(this.student, this.camp));

                // Add camp to attending array in student
                studenDataStore.manageData(new StudentAddAttendingCamp(this.student, this.camp));
                return;
            }
        }
        throw new ObjectNotFoundException("Camp", "DataStore");
    }
    
}
