package com.example.dataservice.student;

import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.datastore.operator.StudentCampRetrival;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;

public class StudentCampDBService implements IStudentCampDBService{

    private Student student;
    
    public StudentCampDBService(Student student){
        this.student = student;
    }
    @Override
	public DataStoreRetrivalOperation<Camp> DSCampRetrival() {
		return new StudentCampRetrival(this.student);
	}
}
