package com.example.dataservice.student;

import com.example.datastore.bilist.BiListDataStore;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.datastore.monolist.operator.StudentCampRetrival;
import com.example.datastructure.Camp;
import com.example.datastructure.Student;
import com.example.utility.DataStorePair;

public class StudentCampDBService implements IStudentCampDBService{

    private Student student;
    
    public StudentCampDBService(Student student){
        this.student = student;
    }
    @Override
	public IMonoListDataStoreRetrivalOperation<Camp> DSCampRetrival(BiListDataStore<DataStorePair<Student,Camp>> scDataStore) {
		return new StudentCampRetrival(this.student,scDataStore);
	}
}
