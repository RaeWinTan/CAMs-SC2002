package com.example.datastore.operator;

import java.util.ArrayList;
import java.util.Date;

import com.example.datastore.IDataStoreEditable;
import com.example.datastore.IDataStoreRetrivable;
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
    IDataStoreEditable<Student> studenDataStorE;
    IDataStoreRetrivable<Student> studentDataStoRe;

    /**
     * Constructor for StudentJoiNCampAsAttendee
     * @param student           Student joining the Camp
     * @param camp              Camp to be joined.
     * @param studentDataStore  Student DataStore, required to update original Student object's attending list.
     */
    public StudentJoinCampAsAttendee(Student student, Camp camp, IDataStoreEditable<Student> studenDataStorE, IDataStoreRetrivable<Student> studentDataStoRe){
        this.student = student;
        this.camp = camp;
        this.studenDataStorE = studenDataStorE;
        this.studentDataStoRe = studentDataStoRe;
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

        // Get latest copy of student 
        this.student = studentDataStoRe.retrieveData(new DataStoreRetrieve<Student>(this.student)).get(0);
        // Chcek for clash in dates
        Date[] camp1Dates = this.camp.getDates();
        for (CampMember campMember : this.student.getAttending()) {
            Date[] camp2Dates = campMember.getCamp().getDates();
            if (camp1Dates[1].before(camp2Dates[0]) || camp1Dates[0].before(camp2Dates[1]))
                throw new IllegalOperationException("Camp date overlaps with another camp student is attending.");
        }

        for (CampMember campMember : this.student.getLeading()) {
            Date[] camp2Dates = campMember.getCamp().getDates();
            if (camp1Dates[1].before(camp2Dates[0]) || camp1Dates[0].before(camp2Dates[1]))
                throw new IllegalOperationException("Camp date overlaps with another camp student is leading.");
        }


        
        for (Camp camp : data) {
            if (camp.isEquals(this.camp)){
                // Add student to attendee array in camp
                camp.getAttendees().add(new CampMember(this.student, camp));

                // Add camp to attending array in student
                this.studenDataStorE.manageData(new StudentAddAttendingCamp(this.student, camp));
                return;
            }
        }
        throw new ObjectNotFoundException("Camp", "DataStore");
    }
    
}
