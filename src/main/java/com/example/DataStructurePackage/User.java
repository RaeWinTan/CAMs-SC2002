package com.example.DataStructurePackage;

import java.util.ArrayList;

import com.example.UtilityPackage.Pair;


// import datastore.IDataStoreObject;

public abstract class User { //implements IDataStoreObject<User>{

	private String userId = null;
	private String password = null;
	private GroupName faculty = null;
	private String name = null;

	/**
	 * Constructor for User class, password will be set to default.
	 * @param userId		Unique Identifier of the user.
	 * @param name			Name of the user.
	 * @param faculty		Faculty the user is from.
	 */
	public User(String userId, String name, GroupName faculty) {
		this.userId= userId;
		this.name = name;
		this.faculty = faculty;
		this.password = "password"; // DEFAULT PASSWORD
	}

	/**
	 * Constructor for User class.
	 * @param userId		Unique Identifier of the user.
	 * @param name			Name of the user.
	 * @param faculty		Faculty the user is from.
	 * @param password		Password of the user.
	 */
	public User(String userId, String name,GroupName faculty,  String password) {
		this.userId= userId;
		this.name = name;
		this.faculty = faculty;
		this.password = password;
	}
	public User(ArrayList<Pair<String, String>> attrMapping){
		for(Pair<String, String> i:attrMapping){
			if(i.getFirst().equals("userId")){
				this.userId = i.getSecond();
			}
			if(i.getFirst().equals("faculty")){
				switch(i.getSecond()){
					case "ADM":
						this.faculty = GroupName.ADM;
						break;
					case "EEE":
						this.faculty = GroupName.EEE;
						break;
					case "NMS":
						this.faculty = GroupName.NMS;
						break;
					case "SCSE":
						this.faculty = GroupName.SCSE;
						break;
					case "SSS":
						this.faculty = GroupName.SSS;
						break;
					default:
						// TODO: Error handling
						//throw new Exception("Invalid faculty: " + facultyStr);
						// TEMPCODE:
						this.faculty = GroupName.NTU;
						
				} 
			}
			
			if(i.getFirst().equals("passowrd")){
				this.password = i.getSecond();
			}
			
		}
		if(this.userId == null || this.faculty==null ){
			//throw error
		}
	}

	/**
	 * Get method for userId.
	 * @return		Unique Identifier of the user.
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Get method for name
	 * @return		Name of the user.
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Get methid for faculty
	 * @return		Faculty the user is from.
	 */
	public GroupName getFaculty() {
		return this.faculty;
	}

	/**
	 * Get method for password
	 * @return		Password of the user.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Set method for password
	 * @param password		Password of the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	} 


	/**
	 * This method returns a copy of the staff.
	 * @return		A copy of the staff.
	 */
	// @Override
	// public abstract User copyOf();

	
	/**
	 * Returns true if o is equal to the user. userId is used for the comparison.
	 * @param o		other user to compare to.
	 * @return		true if o is equal to the user.
	 */
	public boolean isEquals(User o){
		return o.getUserId().equals(this.getUserId());
	}
	//test code 

	public ArrayList<Pair<String, String>> toAttributeValueMapping(){
		ArrayList<Pair<String, String>> rtn = new ArrayList<Pair<String, String>>();
		rtn.add(new Pair<String, String>("userId", this.userId));
		rtn.add(new Pair<String, String>("faculty", this.faculty.toString()));
		rtn.add(new Pair<String, String>("password", this.password));
		return rtn;
	}
	
}