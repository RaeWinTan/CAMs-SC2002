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
 * Camp DataStore edit operator for student to join a camp as an committee member.
 * @see IDataStoreEditOperation
 */
public class StudentJoinCampAsCommittee implements IDataStoreEditOperation<Camp> {

    Student student;
    Camp camp;
    IDataStoreEditable<Student> studenDataStorE;
    IDataStoreRetrivable<Student> studentDataStoRe;

    /**
     * Constructor for StudentJoinCampAsCommittee
     * @param student           Student joining the Camp
     * @param camp              Camp to be joined.
     * @param studentDataStore  Student DataStore, required to update original Student object's leading list.
     */
    public StudentJoinCampAsCommittee(Student student, Camp camp, IDataStoreEditable<Student> studenDataStorE, IDataStoreRetrivable<Student> studentDataStoRe){
        this.student = student;
        this.camp = camp;
        this.studenDataStorE = studenDataStorE;
        this.studentDataStoRe = studentDataStoRe;
    }

    /**
     * Search for Camp and add Student to it's committee list.
     * Call Student DataStore to add Camp to the student's leading list using StudentAddLeadingCamp.
     * @param data  ArrayList of Camp from Camp DataStore.
     * @see StudentAddLeadingCamp
     */
    @Override
    public void perform(ArrayList<Camp> data) {
        // Check if camp has enough slots
        if (this.camp.getRemaindingCommitteeSlots() <= 0)
            throw new IllegalOperationException("Camp has reached the maximum number of committee members.");
        
        // Get latest copy of student.
        this.student = studentDataStoRe.retrieveData(new DataStoreRetrieve<Student>(this.student)).get(0);

        // Check if student is aleady a committee member of another camp
        if (!this.student.getLeading().isEmpty()){
            String otherCampName = this.student.getLeading().get(0).getCamp().getCampName();
            throw new IllegalOperationException("Student is already a committee of another camp - " + otherCampName);
        }

        // Chcek for clash in attending camp dates
        Date[] camp1Dates = this.camp.getDates();
        for (CampMember campMember : this.student.getAttending()) {
            Date[] camp2Dates = campMember.getCamp().getDates();
            if (camp1Dates[1].before(camp2Dates[0]) || camp1Dates[0].before(camp2Dates[1]))
                throw new IllegalOperationException("Camp date overlaps with other camps student is participating in.");
        }
        
        for (Camp camp : data) {
            if (camp.isEquals(this.camp)){
                // Add student to committee array in camp
                camp.getCommittees().add(new CampMember(this.student, this.camp));

                // Add camp to leading array in student
                studenDataStorE.manageData(new StudentAddLeadingCamp(this.student, this.camp));
                return;
            }
        }
        throw new ObjectNotFoundException("Camp", "DataStore");
    }
    
}
