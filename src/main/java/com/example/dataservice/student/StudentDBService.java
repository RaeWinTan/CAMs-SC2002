package com.example.dataservice.student;

import com.example.dataservice.UserDBService;
import com.example.datastore.IDataStoreEditable;
import com.example.datastore.IDataStoreRetrivable;
import com.example.datastore.operator.AdminReplyToEnquiry;
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
import com.example.datastructure.Message;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.utility.Pair;

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
    public IDataStoreEditOperation<Camp> DSJoinCampAsAttendee(Camp camp, IDataStoreEditable<Student> studentDataStorE, IDataStoreRetrivable<Student> studentDataStoRe) {
        return new StudentJoinCampAsAttendee(this.student, camp, studentDataStorE, studentDataStoRe);
    }
    @Override
    public IDataStoreEditOperation<Camp> DSJoinCampAsCommittee(Camp camp, IDataStoreEditable<Student> studentDataStorE, IDataStoreRetrivable<Student> studentDataStoRe) {
        return new StudentJoinCampAsCommittee(this.student, camp, studentDataStorE, studentDataStoRe);
    }

    @Override
    public IDataStoreEditOperation<Camp> DSEnquiryCreate(Enquiry enquiry, IDataStoreEditable<Student> studentDataStore) {
        return new StudentEnquiryCreate(this.student, enquiry, studentDataStore);
    }

	@Override
	public IDataStoreEditOperation<Camp> DSEnquiryReply(Pair<Enquiry,Message> em) {
		return new AdminReplyToEnquiry(this.student, em.getSecond(), em.getFirst());
	}

    @Override
    public IDataStoreEditOperation<Camp> DSSuggestionCreate(Suggestion suggestion, IDataStoreEditable<Student> studentDataStorE, IDataStoreRetrivable<Student> studentDataStoRe){
        return new CommitteeMakeSuggestion(this.student, suggestion, studentDataStorE, studentDataStoRe);
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