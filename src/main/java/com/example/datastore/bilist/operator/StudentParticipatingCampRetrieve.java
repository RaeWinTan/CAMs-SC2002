package com.example.datastore.bilist.operator;

import java.util.ArrayList;

import com.example.UtilityPackage.DataStorePair;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;

public class StudentParticipatingCampRetrieve implements IBiListDataStoreRetrivalOperation<DataStorePair<Student,Camp>> {

    private Student student;
    public StudentParticipatingCampRetrieve(Student student){
        this.student = student;
    }

    /**
     * 
     */
    @Override
    public ArrayList<DataStorePair<Student, Camp>> perform(
    ArrayList<DataStorePair<Student, Camp>> data1,
    ArrayList<DataStorePair<Student, Camp>> data2) {
        
        ArrayList<DataStorePair<Student,Camp>> participating = new ArrayList<DataStorePair<Student,Camp>>();

        data1.forEach(pair->{
            if (pair.getFirst().isEquals(this.student))
                participating.add(pair);
        });

        data2.forEach(pair->{
            if (pair.getFirst().isEquals(this.student))
                participating.add(pair);
        });

        return participating;
    }
    
}
