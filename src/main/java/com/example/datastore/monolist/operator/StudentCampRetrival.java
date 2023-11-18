package com.example.datastore.monolist.operator;

import com.example.datastore.bilist.BiListDataStore;
import com.example.datastore.bilist.operator.StudentParticipatingCampRetrieve;
import com.example.datastructure.Camp;
import com.example.datastructure.GroupName;
import com.example.datastructure.Student;
import com.example.utility.DataStorePair;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
		ArrayList<DataStorePair<Student,Camp>> participating = this.scDataStore.retrieveData(new StudentParticipatingCampRetrieve(this.student));

		return (ArrayList<Camp>) data.stream()
				.filter(i->{
					return this.filter(i,participating);
				})
				.collect(Collectors.toList());
	}

	/**
	 * This method determines if the camp should stay after being filtered.
	 * @param camp	Camp being evaluated.
	 * @return true if camp should stay.
	 */
	private boolean filter(Camp camp, ArrayList<DataStorePair<Student,Camp>> participating) {

		//	Filter by visibility or falcaty.
		if(camp.getVisibility() && (camp.getUserGroup() == GroupName.NTU || camp.getUserGroup() == student.getFaculty())) 
			return true;
		
		// If Student is a participating in the camp (regardless of attendee or committee), return true.
		for(DataStorePair<Student,Camp> scPair:participating) {
			if(scPair.getSecond().isEquals(camp)) 
				return true;
		}
		
		return false;
	}
}