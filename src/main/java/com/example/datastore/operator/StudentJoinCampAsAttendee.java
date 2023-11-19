package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;
import com.example.exception.IllegalOperationException;

public class StudentJoinCampAsAttendee implements DataStoreEditOperation<Camp> {

    Student student;
    Camp camp;
    public StudentJoinCampAsAttendee(Student student, Camp camp){
        this.student = student;
        this.camp = camp;
    }

    @Override
    public void perform(ArrayList<Camp> data) throws IllegalOperationException {
        for (Camp camp : data) {
            if (camp.isEquals(this.camp)){
                camp.getAttendees().add(new CampMember(this.student, this.camp));
                return;
            }
        }

        throw new IllegalOperationException("Camp not found");
    }
    
}
