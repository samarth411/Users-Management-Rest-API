package com.java.users.rest;

public class UserBasicInfo {
	
		private String firstName;
		private String lastName;
		
		private String country;
		
		
		private String creditCardNumber;
		
		
		public UserBasicInfo ()
		{
		}
		
		public String getFirstName() {
			return firstName;
		}
		
		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

	

		public String getCreditCardNumber() {
			return creditCardNumber;
		}

		public void setCreditCardNumber(String creditCardNumber) {
			this.creditCardNumber = creditCardNumber;
		}

		
}
