package com.java.users.rest;
import javax.xml.bind.annotation.XmlRootElement;

public class JsonMessage {
	
	public JsonMessage()
	{
		
	}
	
	String message;
	
	public JsonMessage(String message)
	{
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
