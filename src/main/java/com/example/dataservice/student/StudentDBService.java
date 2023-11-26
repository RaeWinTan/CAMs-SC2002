package com.example.dataservice.student;

import com.example.controllerlibs.ReportFilter;
import com.example.dataservice.UserDBService;
import com.example.datastore.DataStore;
import com.example.datastore.IDataStoreEditable;
import com.example.datastore.IDataStoreRetrivable;
import com.example.datastore.operator.AdminReplyToEnquiry;
import com.example.datastore.operator.AttendeeCampQuit;
import com.example.datastore.operator.CommitteeDeleteSuggestion;
import com.example.datastore.operator.CommitteeEditSuggestion;
import com.example.datastore.operator.CommitteeMakeSuggestion;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastore.operator.IDataStoreRetrivalOperation;
import com.example.datastore.operator.StudentCampRetrival;
import com.example.datastore.operator.StudentEnquiryCreate;
import com.example.datastore.operator.StudentEnquiryDelete;
import com.example.datastore.operator.StudentEnquiryEdit;
import com.example.datastore.operator.StudentGenerateParticipantReport;
import com.example.datastore.operator.StudentJoinCampAsAttendee;
import com.example.datastore.operator.StudentJoinCampAsCommittee;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.utility.Pair;

public class StudentDBService extends UserDBService<Student> implements IStudentCampDBService, IStudentEnquiryDBService, IStudentSuggestionDBService, IStudentReportDBService {

    private Student student;
    
    public StudentDBService(Student student){
        this.student = student;
    }
    @Override
	public IDataStoreRetrivalOperation<Camp> DSCampRetrival() {
		return new StudentCampRetrival(this.student);
	}
    @Override
    public IDataStoreEditOperation<Camp> DSJoinCampAsAttendee(Camp camp, DataStore<Student> studentDS) {
        return new StudentJoinCampAsAttendee(this.student, camp, studentDS,studentDS);
    }
    @Override
    public IDataStoreEditOperation<Camp> DSJoinCampAsCommittee(Camp camp, DataStore<Student> studentDS) {
        return new StudentJoinCampAsCommittee(this.student, camp, studentDS, studentDS);
    }

    @Override
    public IDataStoreEditOperation<Camp> DSQuitCampAsAttendee(Camp camp, IDataStoreEditable<Student> studentDSEditable){
        return new AttendeeCampQuit(this.student, camp, studentDSEditable);
    }

    @Override
    public IDataStoreEditOperation<Camp> DSEnquiryCreate(Enquiry enquiry, IDataStoreEditable<Student> studentDSEditable) {
        return new StudentEnquiryCreate(this.student, enquiry, studentDSEditable);
    }

    @Override
    public IDataStoreEditOperation<Camp> DSEnquiryEdit(Enquiry enquiry) {
        return new StudentEnquiryEdit(this.student, enquiry);
    }

    @Override
    public IDataStoreEditOperation<Camp> DSEnquiryDelete(Enquiry enquiry, IDataStoreEditable<Student> studentDSEditable){
        return new StudentEnquiryDelete(this.student, enquiry, studentDSEditable);
    }

    @Override
	public IDataStoreEditOperation<Camp> DSEnquiryReply(Pair<Enquiry,Message> em) {
		return new AdminReplyToEnquiry(this.student, em.getSecond(), em.getFirst());
	}

    @Override
    public IDataStoreEditOperation<Camp> DSSuggestionCreate(Suggestion suggestion, DataStore<Student> studentDS){
        return new CommitteeMakeSuggestion(this.student, suggestion, studentDS, studentDS);
    }

    @Override 
    public IDataStoreEditOperation<Camp> DSSuggestioNDelete(Suggestion suggestion, IDataStoreEditable<Student> studentDSEditable){
        return new CommitteeDeleteSuggestion(this.student, suggestion, studentDSEditable);
    }

    @Override
    public IDataStoreEditOperation<Camp> DSSuggestionEdit(Suggestion newSuggestion){
        return new CommitteeEditSuggestion(this.student, newSuggestion);
    }

    @Override
	public IDataStoreEditOperation<Camp> DSGenerateParticipantReport(String fileName, IDataStoreRetrivable<Student> studentDSRetrivable, ReportFilter reportFilter){
		return new StudentGenerateParticipantReport(this.student, fileName, reportFilter);
	}
}