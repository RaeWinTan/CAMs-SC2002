package com.example.dataservice.student;

import com.example.dataservice.UserDBService;
import com.example.datastore.IDataStoreEditable;
import com.example.datastore.IDataStoreRetrivable;
import com.example.datastore.operator.AttendeeCampRetrival;
import com.example.datastore.operator.CommitteeCampRetrival;
import com.example.datastore.operator.CommitteeDeleteSuggestion;
import com.example.datastore.operator.CommitteeEditSuggestion;
import com.example.datastore.operator.CommitteeMakeSuggestion;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastore.operator.IDataStoreRetrivalOperation;
import com.example.datastore.operator.StudentCampRetrival;
import com.example.datastore.operator.StudentEnquiryCreate;
import com.example.datastore.operator.StudentJoinCampAsAttendee;
import com.example.datastore.operator.StudentJoinCampAsCommittee;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;

public class StudentDBService extends UserDBService<Student> implements IStudentCampDBService, IStudentEnquiryDBService, IStudentSuggestionDBService {

    private Student student;
    
    public StudentDBService(Student student){
        this.student = student;
    }
    @Override
	public IDataStoreRetrivalOperation<Camp> DSCampRetrival() {
		return new StudentCampRetrival(this.student);
	}
    @Override
    public IDataStoreRetrivalOperation<Camp> DSCommiteeCampRetrival(IDataStoreRetrivable<Student> studentDataStore) {
        return new CommitteeCampRetrival(this.student, studentDataStore);
    }
    @Override
    public IDataStoreRetrivalOperation<Camp> DSAteendeeCampRetrival(IDataStoreRetrivable<Student> studentDataStore) {
        return new AttendeeCampRetrival(this.student, studentDataStore);
    }
    @Override
    public IDataStoreEditOperation<Camp> DSJoinCampAsAttendee(Camp camp, IDataStoreEditable<Student> studentDataStore) {
        return new StudentJoinCampAsAttendee(this.student, camp, studentDataStore);
    }
    @Override
    public IDataStoreEditOperation<Camp> DSJoinCampAsCommittee(Camp camp, IDataStoreEditable<Student> studentDataStore) {
        return new StudentJoinCampAsCommittee(this.student, camp, studentDataStore);
    }

    @Override
    public IDataStoreEditOperation<Camp> DSEnquiryCreate(Enquiry enquiry, IDataStoreEditable<Student> studentDataStore) {
        return new StudentEnquiryCreate(this.student, enquiry, studentDataStore);
    }

    @Override
    public IDataStoreEditOperation<Camp> DSSuggestionCreate(Suggestion suggestion, IDataStoreEditable<Student> studentDataStore){
        return new CommitteeMakeSuggestion(this.student, suggestion, studentDataStore);
    }

    @Override 
    public IDataStoreEditOperation<Camp> DSSuggestioNDelete(Suggestion suggestion, IDataStoreEditable<Student> studentDataStore){
        return new CommitteeDeleteSuggestion(this.student, suggestion, studentDataStore);
    }

    @Override
    public IDataStoreEditOperation<Camp> DSSuggestionEdit(Suggestion newSuggestion){
        return new CommitteeEditSuggestion(this.student, newSuggestion);
    }
}