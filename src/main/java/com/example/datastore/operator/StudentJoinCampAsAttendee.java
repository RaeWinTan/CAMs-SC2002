package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.DataStore;
import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;
import com.example.exception.IllegalOperationException;

public class StudentJoinCampAsAttendee implements IDataStoreEditOperation<Camp> {

    Student student;
    Camp camp;
    DataStore<Student> studenDataStore;
    public StudentJoinCampAsAttendee(Student student, Camp camp, DataStore<Student> studentDataStore){
        this.student = student;
        this.camp = camp;
        this.studenDataStore = studentDataStore;
    }

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

        throw new IllegalOperationException("Camp not found");

        

        
    }
    
}
