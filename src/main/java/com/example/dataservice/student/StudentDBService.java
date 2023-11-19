package com.example.dataservice.student;

import com.example.dataservice.UserDBService;
import com.example.datastore.DataStore;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastore.operator.IDataStoreRetrivalOperation;
import com.example.datastore.operator.StudentCampRetrival;
import com.example.datastore.operator.StudentJoinCampAsAttendee;
import com.example.datastore.operator.StudentJoinCampAsCommittee;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;

public class StudentDBService extends UserDBService<Student> implements IStudentCampDBService {

    private Student student;
    
    public StudentDBService(Student student){
        this.student = student;
    }
    @Override
	public IDataStoreRetrivalOperation<Camp> DSCampRetrival() {
		return new StudentCampRetrival(this.student);
	}
    @Override
    public IDataStoreRetrivalOperation<Camp> DSCommiteeCampRetrival() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSCommiteeCampRetrival'");
    }
    @Override
    public IDataStoreRetrivalOperation<Camp> DSAteendeeCampRetrival() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSAteendeeCampRetrival'");
    }
    @Override
    public IDataStoreEditOperation<Camp> DSJoinCampAsAttendee(Camp camp, DataStore<Student> studentDataStore) {
        return new StudentJoinCampAsAttendee(this.student, camp, studentDataStore);
    }
    @Override
    public IDataStoreEditOperation<Camp> DSJoinCampAsCommittee(Camp camp, DataStore<Student> studentDataStore) {
        return new StudentJoinCampAsCommittee(this.student, camp, studentDataStore);
    }
}