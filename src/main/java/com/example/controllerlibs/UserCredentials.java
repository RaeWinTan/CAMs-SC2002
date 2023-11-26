package com.example.controllerlibs;

/**
 * Class for storing userID, password, and userType, used for logging in.
 */
public class UserCredentials{
	private String userId;
	private String password;
	private UserType userType;

	/** Constructor for UserCredentials Class */
	public UserCredentials(String userId, String password, UserType userType){
		this.userId = userId;
		this.password = password;
		this.userType = userType;
	}

	/** Get method for password */
	public String getPassword() {
		return password;
	}
	
	/** Get method for userID */
	public String getUserId() {
		return userId;
	}

	/** Get method for userType */
	public UserType getUserType() {
		return userType;
	}
}