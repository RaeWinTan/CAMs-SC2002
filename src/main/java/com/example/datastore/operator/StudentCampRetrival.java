package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.GroupName;
import com.example.datastructure.Student;

/**
 * Camp DataStore retrieval operator to retrieve Camps that are visible to a Student.
 * @see IDataStoreRetrivalOperation
 */
public class StudentCampRetrival implements IDataStoreRetrivalOperation<Camp>{

    private Student student;

    /**
     * Constructor for StudentCampRetrival.
     * @param student   Student retrieving camps.
     */
    public StudentCampRetrival(Student student){
        this.student = student;
    }

    /**
     * Filter camps by visibility of Camp and faculty of Student.
     * @param data  Deep clone of ArrayList from Camp DataStore.
     */
    @Override
    public ArrayList<Camp> perform(ArrayList<Camp> data) {
        ArrayList<Camp> relevantCamps = new ArrayList<Camp>();
        for (Camp camp : data) {
            if (camp.getVisibility() && (camp.getUserGroup() == GroupName.NTU || camp.getUserGroup()==this.student.getFaculty())){
                relevantCamps.add(camp);
            }
        }

        return relevantCamps;
    }
    
}
