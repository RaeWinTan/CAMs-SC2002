package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;

public class CommitteeCampRetrival implements IDataStoreRetrivalOperation<Camp> {

    private Student student;

    public CommitteeCampRetrival(Student student){
        this.student = student;
    }

    @Override
    public ArrayList<Camp> perform(ArrayList<Camp> data) {
        ArrayList<CampMember> leading = student.getLeading();
        ArrayList<Camp> relevantCamps = new ArrayList<Camp>();
        for (Camp camp : data) {
            for (CampMember campMember : leading) {
                if (campMember.getCamp().isEquals(camp)){
                    relevantCamps.add(camp);
                    break;
                }
            }
        }
        return relevantCamps;
    }
}