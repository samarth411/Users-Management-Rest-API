package com.java.users.rest;

/**
 * Represents  user information with canonical links in a sytem
 */

public class UserBasicInfoLinks {
	
		private ValueElement firstName;
		
		private ValueElement lastName;
		
		private ValueElement country;
	
		
		private ValueElement creditCardNumber;
		
		public UserBasicInfoLinks ()
		{
		}
		
		
		
		public ValueElement getFirstName() {
			return firstName;
		}


		public void setFirstName(ValueElement firstName) {
			this.firstName = firstName;
		}


		public ValueElement getLastName() {
			return lastName;
		}


		public void setLastName(ValueElement lastName) {
			this.lastName = lastName;
		}


		public ValueElement getCountry() {
			return country;
		}


		public void setCountry(ValueElement country) {
			this.country = country;
		}


		public ValueElement getCreditCardNumber() {
			return creditCardNumber;
		}


		public void setCreditCardNumber(ValueElement creditCardNumber) {
			this.creditCardNumber = creditCardNumber;
		}


		
}
