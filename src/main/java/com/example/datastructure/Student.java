package com.example.datastructure;

import com.example.dataservice.*;
import com.example.datastore.IDataStoreObject;

public class Student extends User implements IDataStoreObject<Student>{

	private StudentDBService dbService;

	/**
	 * Constructor for Student class, password will be set to default.
	 * @param userId		Unique Identifier of the student.
	 * @param name			Name of the student.
	 * @param faculty		Faculty the student is from.
	 */
	public Student(String userId, String name, GroupName faculty) {
		super(userId, name, faculty);
		this.dbService = new StudentDBService(this);
	}

	/**
	 * Constructor for Student class.
	 * @param userId		Unique Identifier of the student.
	 * @param name			Name of the student.
	 * @param faculty		Faculty the student is from.
	 * @param password		Password of the student.
	 */
	public Student(String userId, String name, GroupName faculty, String password) {
		super(userId, name, faculty, password);
		this.dbService = new StudentDBService(this);
		
	}

	/**
	 * Get method for dbService.
	 * @return		dbService.
	 */
	public StudentDBService getDbService() {
		return this.dbService;
	}

	/**
	 * This method returns a copy of the student.
	 * @return		A copy of the student.
	 */
	@Override
	public Student copyOf() {
		return new Student(this.getUserId(), this.getName(), this.getFaculty(), this.getPassword());
	}
	
	/**
	 * Returns true if o is equal to the user. userId is used for the comparison.
	 * @param o		other user to compare to.
	 * @return		true if o is equal to the user.
	 */
	@Override
	public boolean isEquals(Student o) {
		return super.isEquals(o);
	}
}