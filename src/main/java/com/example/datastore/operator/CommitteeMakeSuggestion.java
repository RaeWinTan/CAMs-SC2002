package com.example.datastore.operator;

import java.util.ArrayList;

import com.example.datastore.IDataStoreEditable;
import com.example.datastore.IDataStoreRetrivable;
import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.exception.InsufficientPermissionException;
import com.example.exception.ObjectNotFoundException;

/**
 * Camp DataStore edit operator for making a Suggestion.
 * @see IDataStoreEditOperation
 */
public class CommitteeMakeSuggestion implements IDataStoreEditOperation<Camp> {

    private Student student;
    private Suggestion suggestion;
    private IDataStoreEditable<Student> studentDataStorE;
    private IDataStoreRetrivable<Student> studentDataStoRe;

    /**
     * Constructor for CommitteeMakeSuggestion.
     * @param student           Student making the suggestion.
     * @param suggestion        Suggestion being made.
     * @param studentDataStore  Student DataStore, required for appending suggestion to original Student object.
     */
    public CommitteeMakeSuggestion(Student student, Suggestion suggestion, IDataStoreEditable<Student> studentDataStorE, IDataStoreRetrivable<Student> studentDataStoRe){
        this.student = student;
        this.suggestion = suggestion;
        this.studentDataStorE = studentDataStorE;
        this.studentDataStoRe = studentDataStoRe;
    }

    /**
     * Append suggestion to appropriate Camp from Camp DataStore.
     * Also call studentDataStore to append suggestion to student using CommitteeAddSuggestion operation.
     * @param data  ArrayList of Camps from Camp DataStore.
     * @see CommitteeAddSuggestion
     */
    @Override
    public void perform(ArrayList<Camp> data) {
        // Get latest copy of student
        this.student = this.studentDataStoRe.retrieveData(new DataStoreRetrieve<Student>(this.student)).get(0);
        
        // Check if student has permission.
        boolean isCommittee = false;
        for (CampMember campMember : this.student.getLeading()) {
            if (campMember.getCamp().isEquals(this.suggestion.getCamp())){
                isCommittee = true;
                break;
            }
        }
        if (!isCommittee)
            throw new InsufficientPermissionException("Student is not a committee member of the Camp they're making Suggestion for.");
        
        // Get camp
        for (Camp camp : data) {
            if (camp.isEquals(this.suggestion.getCamp())){
                // Add Suggestion to Student
                this.studentDataStorE.manageData(new CommitteeAddSuggestion(this.student, this.suggestion));
                
                // Increase student points
                this.studentDataStorE.manageData(new StudentIncreasePoint(this.student));

                // Add Suggestion to Camp;
                camp.getSuggestions().add(this.suggestion);
                return;
            }
        }
        throw new ObjectNotFoundException("Camp", "DataStore");
    }
}
