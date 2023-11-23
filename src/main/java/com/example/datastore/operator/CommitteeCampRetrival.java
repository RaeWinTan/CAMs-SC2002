package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreRetrivable;
import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;

/**
 * Camp DataStore retrival operator for retriving camps that student is committee of.
 * @see IDataStoreRetrivalOperation
 */
// TODO: This class may be redundant. #1
public class CommitteeCampRetrival implements IDataStoreRetrivalOperation<Camp> {

    private Student student;
    private IDataStoreRetrivable<Student> studentDataStore;

    /**
     * Constructor for CommitteeCampRetrival.
     * @param student           Student performing this retrival.
     * @param studentDataStore  Student DataStore, required for retriving latest copy of student object to get list of camps they are committee of.
     */
    public CommitteeCampRetrival(Student student, IDataStoreRetrivable<Student> studentDataStore){
        this.student = student;
        this.studentDataStore = studentDataStore;
    }

    /**
     * Filter the Data from Camp DataStore by if the student is committee of the camp.
     * Retrieve the latest student Object using DataStoreRetrieve to get access to a list of camps attended by student.
     * @param data  Deep clone of ArrayList of Camps from Camp DataStore.
     * @see DataStoreRetrieve
     */
    @Override
    public ArrayList<Camp> perform(ArrayList<Camp> data) {
        // get latest copy of student object
        this.student = studentDataStore.retrieveData(new DataStoreRetrieve<Student>(this.student)).get(0);
        
        // get camps that student is a committee in (array should be of size 1
        // due to constraints in client requirements, but can be easily lifted.)
        ArrayList<CampMember> leading = this.student.getLeading();

        // create new array of camps and add latest copies of camp to this array.
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