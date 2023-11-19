package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;
import com.example.exception.IllegalOperationException;
import com.example.exception.ObjectNotFoundException;

public class StudentJoinCampAsCommittee implements IDataStoreEditOperation<Camp> {

    Student student;
    Camp camp;
    IDataStoreEditable<Student> studenDataStore;
    public StudentJoinCampAsCommittee(Student student, Camp camp, IDataStoreEditable<Student> studentDataStore){
        this.student = student;
        this.camp = camp;
        this.studenDataStore = studentDataStore;
    }

    @Override
    public void perform(ArrayList<Camp> data) {
        // Check if camp has enough slots
        if (this.camp.getRemaindingCommitteeSlots() <= 0){
            throw new IllegalOperationException("Camp has reached the maximum number of committee members.");
        }

        // Check if student is aleady a committee member of another camp
        if (!this.student.getLeading().isEmpty()){
            String otherCampName = this.student.getLeading().get(0).getCamp().getCampName();
            throw new IllegalOperationException("Student is already a committee of another camp - " + otherCampName);
        }
        
        for (Camp camp : data) {
            if (camp.isEquals(this.camp)){
                // Add student to committee array in camp
                camp.getCommittees().add(new CampMember(this.student, this.camp));

                // Add camp to leading array in student
                studenDataStore.manageData(new StudentAddLeadingCamp(this.student, this.camp));
                return;
            }
        }
        throw new ObjectNotFoundException("Camp", "DataStore");
    }
    
}
