package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.exception.ObjectNotFoundException;

/**
 * Camp DataStore edit operator for attendee to quit camp.
 */
public class AttendeeCampQuit implements IDataStoreEditOperation<Camp>{

    Student student;
    Camp camp;
    IDataStoreEditable<Student> studentDSEditable;

    /**
     * Constructor for AttendeeCampQuit.
     * @param student           Student quiting camp.
     * @param camp              Camp being quit.
     * @param studentDSEditable Student DataStore, required for removing Camp from Student.
     */
    public AttendeeCampQuit(Student student, Camp camp, IDataStoreEditable<Student> studentDSEditable){
        this.student = student;
        this.camp = camp;
        this.studentDSEditable = studentDSEditable;
    }

    /**
     * Search for Camp and remove Student from Camp attending list.
     * Call Student DataStore to remove Camp from Student using AttendeeRemoveCamp.
     * @param data      ArrayList of Camp from Camp DataStore.
     * @see AttendeeRemoveCamp
     */
    @Override
    public void perform(ArrayList<Camp> data) {
        for (Camp camp : data) {
            if (camp.isEquals(this.camp)){
                if(camp.getAttendees().removeIf(cm->cm.getStudent().isEquals(this.student))){
                    studentDSEditable.manageData(new AttendeeRemoveCamp(this.student, camp));
                    return;
                }
                throw new ObjectNotFoundException("Student", "Camp");
            }
        }
        throw new ObjectNotFoundException("Camp", "DataStore");
    }
    
}
