package com.example.datastore.associative;

import java.util.ArrayList;

import com.example.DataStructurePackage.Camp;
import com.example.DataStructurePackage.Student;
import com.example.UtilityPackage.Pair;

public class StudentCamp_DataStore implements IAssociativeDataStore<Student, Camp>{

	private ArrayList<Pair<Student, Camp>> committee_camp;
	private ArrayList<Pair<Student, Camp>> attendee_camp;

	public StudentCamp_DataStore(){
		this.committee_camp = new ArrayList<Pair<Student, Camp>>();
		this.attendee_camp = new ArrayList<Pair<Student, Camp>>();
	}

	/**
	 * 
	 * @param obj
	 * @throws Exception 
	 * list1 = committee_camp
	 */
	public void addList1(Pair<Student, Camp> obj) throws Exception{
		// TODO - implement StudentCamp_DataStore.addList1
		if(checkClash(obj)) throw new Exception("Got clash either timing clash or already a committee of some camp");
		committee_camp.add(obj);
	}

	/**
	 * 
	 * @param obj
	 * list2 = attenddee_camp
	 */
	public void addList2(Pair<Student, Camp> obj) throws Exception{
		if(checkClash(obj)) throw new Exception("Got clash Either timing clash or committee of this camp");
		attendee_camp.add(obj);
	}

	public ArrayList<Pair<Student, Camp>> getList1() {
		return (ArrayList<Pair<Student, Camp>>)committee_camp.clone();
	}

	public ArrayList<Pair<Student, Camp>> getList2() {
		return (ArrayList<Pair<Student, Camp>>)attendee_camp.clone();
	}

	/**
	 * 
	 * @param obj
	 */
	private boolean checkClash(Pair<Student, Camp> obj) {
		//dates put into two sets
		//two sets add to gether if length dont add up return true
		//timing clash
		
		return false;
	}
}