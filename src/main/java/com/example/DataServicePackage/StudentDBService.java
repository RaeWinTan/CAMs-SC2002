package com.example.DataServicePackage;

import com.example.DataStoreOperatorPackage.RetrivalOperator.StudentCampRetrival;
import com.example.DataStructurePackage.Camp;
import com.example.DataStructurePackage.Student;
import com.example.UtilityPackage.DataStorePair;
import com.example.datastore.bilist.BiListDataStore;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;

public class StudentDBService extends UserDBService<Student> implements ICampStudentDataServicable{

	
	private Student student;

	public StudentDBService(Student s) {
		this.student = s;
	}

	@Override
	public IMonoListDataStoreRetrivalOperation<Camp> DSCampRetrival(BiListDataStore<DataStorePair<Student,Camp>> scDataStore) {
		return new StudentCampRetrival(student,scDataStore);
	}
}