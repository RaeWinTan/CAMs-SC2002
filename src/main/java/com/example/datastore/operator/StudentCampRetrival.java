package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.GroupName;
import com.example.datastructure.Student;

public class StudentCampRetrival implements IDataStoreRetrivalOperation<Camp>{

    private Student student;
    public StudentCampRetrival(Student student){
        this.student = student;
    }

    @Override
    public ArrayList<Camp> perform(ArrayList<Camp> data) {
        ArrayList<Camp> relevantCamps = new ArrayList<Camp>();

        for (Camp camp : relevantCamps) {
            if (camp.getVisibility() && (camp.getUserGroup() == GroupName.NTU || camp.getUserGroup()==this.student.getFaculty())){
                relevantCamps.add(camp);
            }
        }

        return relevantCamps;
    }
    
}
