package com.example.DataStoreOperatorPackage.RetrivalOperator;

import com.example.UtilityPackage.DataStorePair;
import com.example.datastore.bilist.BiListDataStore;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.DataStructurePackage.Camp;
import com.example.DataStructurePackage.Student;

public class StudentCampRetrival implements IMonoListDataStoreRetrivalOperation<Camp> {
	private Student student;
	private BiListDataStore<DataStorePair<Student,Camp>> scDataStore;

	/**
	 * Constructor for StudentCampRetrival class.
	 * @param student		Student performing the camp retrival.
	 * @param SCdatastore	Student-Camp datastore reference used to determine if student is a committee of a camp.
	 */
	public StudentCampRetrival(Student student, BiListDataStore<DataStorePair<Student,Camp>> scDataStore) {
		this.student = student;
		this.scDataStore = scDataStore;
	}

	/**
	 * This method filters the camps from Camp DataStore according to whether the Student is able to view it.
	 * @param data		Clone of ArrayList of Camps from Camp DataStore.
	 * @return 			Deep clone of filtered data. 
	 * @see #filter(Camp)
	 * @see IMonoListDataStoreRetrivalOperation
	 */
	public ArrayList<Camp> perform(ArrayList<Camp> data) {		
		return (ArrayList<Camp>) data.stream().
				filter(i->{
					return this.filter(i);
				})
				.map(i->i.copyOf())
				.collect(Collectors.toList());
	}

	/**
	 * This method determines if the camp should stay after being filtered.
	 * @param camp	Camp being evaluated.
	 * @return true if camp should stay.
	 */
	private boolean filter(Camp camp) {
		return true;
		// //	Filter by (isCommitte of that camp) or (inFaculty and visible).
		// if(camp.getVisibility() && camp.getUserGroup() == student.getFaculty()) return true;

		// // TODO: Replace .getList1 with appropriate operators
		// ArrayList<Pair<Student, Camp>> committee_camp =  this.scDataStore.getList1();
		// // ArrayList<Pair<Student, Camp>> attendee_camp =  this.studentDBService.getScDataStore().getList2();
		
		// // If Student is a committee of the camp, return true.
		// for(Pair<Student,Camp>i:committee_camp) {
		// 	if(i.getFirst().isEquals(student) && i.getSecond().isEquals(camp)) return true;
		// }
		
		// return false;
	}



}