package com.java.users.rest;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents list of users
 */

public class UsersList {
	
	public UsersList (List<String> usersList)
	{
		this.usersList = usersList;
	}
	
	private List<String> usersList;

	public List<String> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<String> usersList) {
		this.usersList = usersList;
	}
	

}