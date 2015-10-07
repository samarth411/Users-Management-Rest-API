package com.java.users.rest;
import javax.xml.bind.annotation.XmlRootElement;

public class User {
	
	public User ()
	{
	}
	
	public User (String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}
	
	private String userName;
	private String password;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
