package com.example.controllerlibs;
public class UserCredentials{
	private String userId;
	private String password;
	private UserType userType;
	public UserCredentials(String userId, String password, UserType userType){
		this.userId = userId;
		this.password = password;
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public String getUserId() {
		return userId;
	}
	public UserType getUserType() {
		return userType;
	}
}