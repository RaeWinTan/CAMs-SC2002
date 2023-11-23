package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreRetrivable;
import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;

/**
 * Camp DataStore retrival operator for retriving camps that student is attending (excludes committee).
 * @see IDataStoreRetrivalOperation
 */
// TODO: This class may be redundant. #1
public class AttendeeCampRetrival implements IDataStoreRetrivalOperation<Camp> {

    private Student student;
    private IDataStoreRetrivable<Student> studentDataStore;

    /**
     * Constructor for AttendeeCampRetrival.
     * @param student           Student performing this retrival.
     * @param studentDataStore  Student DataStore, required for retriving latest copy of student object to get list of camps they are attending.
     */
    public AttendeeCampRetrival(Student student, IDataStoreRetrivable<Student> studentDataStore){
        this.student = student;
        this.studentDataStore = studentDataStore;
    }

    /**
     * Filter the Data from Camp DataStore by if the student is attending the camp. 
     * Retrieve the latest student Object using DataStoreRetrieve to get access to a list of camps attended by student.
     * @param data  Deep clone of ArrayList of Camps from Camp DataStore.
     * @see DataStoreRetrieve
     */
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
