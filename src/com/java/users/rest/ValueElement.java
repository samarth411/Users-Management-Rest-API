package com.java.users.rest;

/**
 * Represents a given piece of information
 */

public class ValueElement {
	
	public ValueElement ()
	{
	}
	
	public ValueElement (String value, String canonicalLink)
	{
		this.value = value;
		this.canonicalLink = canonicalLink;
	}
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCanonicalLink() {
		return canonicalLink;
	}
	public void setCanonicalLink(String canonicalLink) {
		this.canonicalLink = canonicalLink;
	}
	private String value;
	private String canonicalLink;
	
	

}
