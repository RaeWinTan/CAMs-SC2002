package com.example.datastructure;

import java.util.ArrayList;

import com.example.datastore.IDataStoreObject;
import com.example.utility.Pair;


public class Staff extends User implements IDataStoreObject<Staff>{
	private ArrayList<Message> repliedTo;
	private ArrayList<Camp> campsCreated;
	/**
	 * Constructor for Staff class, password will be set to default.
	 * @param userId		Unique Identifier of the staff.
	 * @param name			Name of the staff.
	 * @param faculty		Faculty the staff is from.
	 */
	public Staff(String userId, String name, GroupName faculty) {
		super(userId, name, faculty);
	}


	/**
	 * Constructor for Staff class.
	 * @param userId		Unique Identifier of the staff.
	 * @param name			Name of the staff.
	 * @param faculty		Faculty the user is staff.
	 * @param password		Password of the staff.
	 */
	private Staff(String userId, String name, GroupName faculty, String password, ArrayList<Message> repliedTo, ArrayList<Camp> campsCreated) {
		super(userId, name, faculty, password);
		this.repliedTo = repliedTo;
		this.campsCreated = campsCreated;
	}

	public ArrayList<Message> getRepliedTo(){
		return this.repliedTo;
	}

	public ArrayList<Camp> getCampsCreated(){
		return this.campsCreated;
	}

	/**
	 * This method returns a copy of the staff.
	 * @return		A copy of the staff.
	 */
	@Override
	public Staff copyOf() {
		return new Staff(this.getUserId(), this.getName(), this.getFaculty(), this.getPassword(), (ArrayList<Message>)this.getRepliedTo().clone(), (ArrayList<Camp>)this.getCampsCreated().clone());
	}

	
	/**
	 * Returns true if o is equal to the user. userId is used for the comparison.
	 * @param o		other user to compare to.
	 * @return		true if o is equal to the user.
	 */
	@Override
	public boolean isEquals(Staff o) {
		return super.isEquals(o);
	}

	public ArrayList<Pair<String, String>> toAttributeValueMapping(){
		ArrayList<Pair<String, String>> rtn = new ArrayList<Pair<String, String>>();
		rtn.add(new Pair<String, String>("name", this.getName()));
		rtn.add(new Pair<String, String>("faculty", this.getFaculty().toString()));
		
		return rtn;
	}

	
	
}