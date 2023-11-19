package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;

public class AttendeeCampRetrival implements IDataStoreRetrivalOperation<Camp> {

    private Student student;

    public AttendeeCampRetrival(Student student){
        this.student = student;
    }

    @Override
    public ArrayList<Camp> perform(ArrayList<Camp> data){
        ArrayList<CampMember> attending = student.getAttending();
        ArrayList<Camp> relevantCamps = new ArrayList<Camp>();
        for (Camp camp : data) {
            for (CampMember campMember : attending) {
                if (campMember.getCamp().isEquals(camp)){
                    relevantCamps.add(camp);
                    break;
                }
            }
        }
        return relevantCamps;
    }
    
}
