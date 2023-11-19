package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreRetrivable;
import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;

public class AttendeeCampRetrival implements IDataStoreRetrivalOperation<Camp> {

    private Student student;
    private IDataStoreRetrivable<Student> studentDataStore;

    public AttendeeCampRetrival(Student student, IDataStoreRetrivable<Student> studentDataStore){
        this.student = student;
        this.studentDataStore = studentDataStore;
    }

    @Override
    public ArrayList<Camp> perform(ArrayList<Camp> data){
        // get latest copy of student object
        this.student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(this.student)).get(0);
        
        // get camps that student is attending
        ArrayList<CampMember> attending = this.student.getAttending();

        // create new array of camps and add latest copies of camp to this array.
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
