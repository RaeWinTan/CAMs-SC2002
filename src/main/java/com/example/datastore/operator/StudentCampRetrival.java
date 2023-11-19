package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.GroupName;
import com.example.datastructure.Student;

public class StudentCampRetrival implements DataStoreRetrivalOperation<Camp>{

    private Student student;
    public StudentCampRetrival(Student student){
        this.student = student;
    }

    @Override
    public ArrayList<Camp> perform(ArrayList<Camp> data) {
        ArrayList<Camp> relevantCamps = new ArrayList<Camp>();
        ArrayList<CampMember> attending = this.student.getAttending();
        ArrayList<CampMember> leading = this.student.getLeading();


        for (Camp camp : relevantCamps) {
            if (camp.getVisibility() && (camp.getUserGroup() == GroupName.NTU || camp.getUserGroup()==this.student.getFaculty())){
                relevantCamps.add(camp);
                continue;
            }

            for (CampMember campMember : attending){
                if (campMember.getCamp().isEquals(camp)){
                    relevantCamps.add(camp);
                    break;
                }
            }

            for (CampMember campMember : leading){
                if (campMember.getCamp().isEquals(camp)){
                    relevantCamps.add(camp);
                    break;
                }
            }
        }


        return relevantCamps;
    }
    
}
