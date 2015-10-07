package com.java.users.database;

import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.QueryParam;

import com.java.users.rest.User;
import com.java.users.rest.UserBasicInfo;
import com.java.users.rest.UserBasicInfoLinks;
import com.java.users.rest.ValueElement;

public class DatabaseService {

	public static Connection connection;
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    //static final String DB_URL = "jdbc:mysql://localhost:3306/";
    
    static final String DB_URL = "jdbc:mysql://usersmanagement123.c2snbmfpd8nh.us-west-2.rds.amazonaws.com:3306/";
    
    
    static final String DB_NAME = "users";
    
    static final String USER = "users_123";
    static final String PASS = "123_Eureka";
    
    
    
    
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		createDatabase(DB_NAME);
		
		createUsersTable();
		
		createUsersInfoTable();
	
    }
	
	/**
	 * Create database
	 */
	 public static void createDatabase(String databaseName) {
  	   Connection conn = null;
  	   Statement stmt = null;
  	   try{
  	      //STEP 2: Register JDBC driver
  	      Class.forName("com.mysql.jdbc.Driver");

  	      //STEP 3: Open a connection
  	      System.out.println("Creating database..." + databaseName);
  	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
  	      
  	      stmt = conn.createStatement();
  	      String sql = "CREATE DATABASE " + databaseName; 

  	      stmt.executeUpdate(sql);
  	      System.out.println("Created database successfully...");

  	   }catch(SQLException se){
  	      //Handle errors for JDBC
  	      se.printStackTrace();
  	   }catch(Exception e){
  	      //Handle errors for Class.forName
  	      e.printStackTrace();
  	   }finally{
  	      //finally block used to close resources
  	      try{
  	         if(stmt!=null)
  	            conn.close();
  	      }catch(SQLException se){
  	      }// do nothing
  	      try{
  	         if(conn!=null)
  	            conn.close();
  	      }catch(SQLException se){
  	         se.printStackTrace();
  	      }//end finally try
  	   }//end try
  	   System.out.println("Goodbye!");
  	}//end main
	 
	 /**
    * Create users table
     */
	 public static void createUsersTable() {
	  	   Connection conn = null;
	  	   Statement stmt = null;
	  	   try{
	  	      //STEP 2: Register JDBC driver
	  	      Class.forName("com.mysql.jdbc.Driver");

	  	      //STEP 3: Open a connection
	  	      System.out.println("Connecting to a selected database...");
	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
	  	      System.out.println("Connected database successfully...");
	  	      stmt = conn.createStatement();
	  	      
	  	      String sql = "CREATE TABLE users "+
	                    "(userid BIGINT not NULL AUTO_INCREMENT,  " +
	                    " username VARCHAR(255) not NULL, " +
	                    " password VARCHAR(255), " + 
	                    " PRIMARY KEY ( userid ))"; 

	  	      stmt.executeUpdate(sql);
	  	      System.out.println("Created table users in given database...");

	  	   }catch(SQLException se){
	  	      //Handle errors for JDBC
	  	      se.printStackTrace();
	  	   }catch(Exception e){
	  	      //Handle errors for Class.forName
	  	      e.printStackTrace();
	  	   }finally{
	  	      //finally block used to close resources
	  	      try{
	  	         if(stmt!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	      }// do nothing
	  	      try{
	  	         if(conn!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	         se.printStackTrace();
	  	      }//end finally try
	  	   }//end try
	  	   System.out.println("Goodbye!");
	  	}//end main
	 
	 
	 /**
	 * Create userinfo table
	 */
	 public static void createUsersInfoTable() {
	  	   Connection conn = null;
	  	   Statement stmt = null;
	  	   try{
	  	      //STEP 2: Register JDBC driver
	  	      Class.forName("com.mysql.jdbc.Driver");

	  	      //STEP 3: Open a connection
	  	      System.out.println("Connecting to a selected database...");
	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
	  	      System.out.println("Connected database successfully...");
	  	      stmt = conn.createStatement();
	  	      
	  	      String sql = "CREATE TABLE userinfo "+
	                    "(username VARCHAR(255) not NULL, " +
	                    " firstname VARCHAR(255) , " +
	                    " lastname VARCHAR(255) , " +
	                    " country VARCHAR(255) , " +
	                    " creditCardNumber VARCHAR(255) , " +
	                    " PRIMARY KEY ( username ))"; 
	  	      stmt.executeUpdate(sql);
	  	      System.out.println("Created table users info in given database...");

	  	   }catch(SQLException se){
	  	      //Handle errors for JDBC
	  	      se.printStackTrace();
	  	   }catch(Exception e){
	  	      //Handle errors for Class.forName
	  	      e.printStackTrace();
	  	   }finally{
	  	      //finally block used to close resources
	  	      try{
	  	         if(stmt!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	      }// do nothing
	  	      try{
	  	         if(conn!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	         se.printStackTrace();
	  	      }//end finally try
	  	   }//end try
	  	   System.out.println("Goodbye!");
	  	}//end main
	 
	 
	 
	 /**
	 * Insert user
	 */
	 public static void insertUser(User user) {
	  	   Connection conn = null;
	  	   Statement stmt = null;
	  	   try{
	  	      //STEP 2: Register JDBC driver
	  	      Class.forName("com.mysql.jdbc.Driver");

	  	      //STEP 3: Open a connection
	  	      //System.out.println("Connecting to a selected database...");
	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
	  	      //System.out.println("Connected database successfully...");
	  	      
	  	      //STEP 4: Execute a query
	  	      //System.out.println("Inserting records into the table...");
	  	      stmt = conn.createStatement();
	  	      PreparedStatement st;

	  	      st = conn.prepareStatement("insert into users (username, password) values(?,?)");
	  	      //now you bind the data to your parameters
	  	      st.setString(1, user.getUserName());
	  	      st.setString(2, user.getPassword());
				 
	  	     
	  	      //and then you can execute it
	  	      st.executeUpdate();
	  	      //System.out.println("Inserted records into the table...");

	  	   }catch(SQLException se){
	  	      //Handle errors for JDBC
	  	      se.printStackTrace();
	  	   }catch(Exception e){
	  	      //Handle errors for Class.forName
	  	      e.printStackTrace();
	  	   }finally{
	  	      //finally block used to close resources
	  	      try{
	  	         if(stmt!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	      }// do nothing
	  	      try{
	  	         if(conn!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	         se.printStackTrace();
	  	      }//end finally try
	  	   }//end try
	  	   System.out.println("User inserted...");
	  	   System.out.println("Goodbye!");
	  	}
	 
	 /**
	  * Insert user info
	  */
	 public static void insertUserInfo(User user) {
	  	   Connection conn = null;
	  	   Statement stmt = null;
	  	   try{
	  	      //STEP 2: Register JDBC driver
	  	      Class.forName("com.mysql.jdbc.Driver");

	  	      //STEP 3: Open a connection
	  	      //System.out.println("Connecting to a selected database...");
	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
	  	      //System.out.println("Connected database successfully...");
	  	      
	  	      //STEP 4: Execute a query
	  	      //System.out.println("Inserting records into the table...");
	  	      stmt = conn.createStatement();
	  	      PreparedStatement st;

	  	      st = conn.prepareStatement("insert into userinfo (username) values(?)");
	  	      //now you bind the data to your parameters
	  	      st.setString(1, user.getUserName());
				 
	  	     
	  	      //and then you can execute it
	  	      st.executeUpdate();
	  	      //System.out.println("Inserted records into the table...");

	  	   }catch(SQLException se){
	  	      //Handle errors for JDBC
	  	      se.printStackTrace();
	  	   }catch(Exception e){
	  	      //Handle errors for Class.forName
	  	      e.printStackTrace();
	  	   }finally{
	  	      //finally block used to close resources
	  	      try{
	  	         if(stmt!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	      }// do nothing
	  	      try{
	  	         if(conn!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	         se.printStackTrace();
	  	      }//end finally try
	  	   }//end try
	  	   System.out.println("User inserted...");
	  	   System.out.println("Goodbye!");
	  	}
	 
	 /**
      * Authenticate user
	  */
	 public static boolean authenticateUser(User user) {
	  	   Connection conn = null;
	  	   Statement stmt = null;
	  	   try{
	  	      //STEP 2: Register JDBC driver
	  	      Class.forName("com.mysql.jdbc.Driver");

	  	      //STEP 3: Open a connection
	  	      //System.out.println("Connecting to a selected database...");
	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
	  	      //System.out.println("Connected database successfully...");
	  	      
	  	      //STEP 4: Execute a query
	  	      //System.out.println("Inserting records into the table...");
	  	      stmt = conn.createStatement();
	  	      PreparedStatement st;

	  	      String sql = "SELECT * from users  where username = '" + user.getUserName() + "' and password = '" + user.getPassword() + "'";
	  	      System.out.println(sql);
	  	      
	  	      //System.out.println(sql);
	  	      ResultSet rs = stmt.executeQuery(sql);
	  	      
		  	  if (!rs.next() ) {
		  	      return false;
		  	  }
		  	  else
		  	  {
		  		  return true;
		  	  }
	  	    
	  	   }catch(SQLException se){
	  	      //Handle errors for JDBC
	  	      se.printStackTrace();
	  	   }catch(Exception e){
	  	      //Handle errors for Class.forName
	  	      e.printStackTrace();
	  	   }finally{
	  	      //finally block used to close resources
	  	      try{
	  	         if(stmt!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	      }// do nothing
	  	      try{
	  	         if(conn!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	         se.printStackTrace();
	  	      }//end finally try
	  	   }//end try
		return false;
	  	}
	 
	 
	 /**
	 * Validate duplicate user
	 */
	 public static boolean validateDuplicateUser(User user) {
	  	   Connection conn = null;
	  	   Statement stmt = null;
	  	   try{
	  	      //STEP 2: Register JDBC driver
	  	      Class.forName("com.mysql.jdbc.Driver");

	  	      //STEP 3: Open a connection
	  	      //System.out.println("Connecting to a selected database...");
	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
	  	      //System.out.println("Connected database successfully...");
	  	      
	  	      //STEP 4: Execute a query
	  	      //System.out.println("Inserting records into the table...");
	  	      stmt = conn.createStatement();
	  	      PreparedStatement st;

	  	      String sql = "SELECT * from users  where username = '" + user.getUserName() + "'";
	  	      System.out.println(sql);
	  	      
	  	      //System.out.println(sql);
	  	      ResultSet rs = stmt.executeQuery(sql);
	  	      
		  	  if (rs.next() ) {
		  	      return true;
		  	  }
		  	  else
		  	  {
		  		  return false;
		  	  }
	  	    
	  	   }catch(SQLException se){
	  	      //Handle errors for JDBC
	  	      se.printStackTrace();
	  	   }catch(Exception e){
	  	      //Handle errors for Class.forName
	  	      e.printStackTrace();
	  	   }finally{
	  	      //finally block used to close resources
	  	      try{
	  	         if(stmt!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	      }// do nothing
	  	      try{
	  	         if(conn!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	         se.printStackTrace();
	  	      }//end finally try
	  	   }//end try
		return false;
	  	}
	 
	 
	 /**
	 * Validate root user
	 */
	 public static boolean validateRootUser(String username, String password)
	 {
		 // Check password also
		  if (username.equals("root"))
		  {
			  return true ;
		  }
		  else
		  {
			  return false;
		  }
		  
	 }
	 
	 
	 
	 
	 
	 /**
	 * Return list of users
	 */
	 public static List<String> getUsers() {
	  	   Connection conn = null;
	  	   Statement stmt = null;
	  	   List<String> users = new ArrayList<String>();
	  	   
	  	   try{
	  	      //STEP 2: Register JDBC driver
	  	      Class.forName("com.mysql.jdbc.Driver");

	  	      //STEP 3: Open a connection
	  	      //System.out.println("Connecting to a selected database...");
	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
	  	      //System.out.println("Connected database successfully...");
	  	      
	  	      //STEP 4: Execute a query
	  	      //System.out.println("Inserting records into the table...");
	  	      stmt = conn.createStatement();
	  	      PreparedStatement st;

	  	      String sql = "SELECT * from users ";
	  	      System.out.println(sql);
	  	      
	  	      //System.out.println(sql);
	  	      ResultSet rs = stmt.executeQuery(sql);

	  	    while(rs.next()){
		         //Retrieve by column name
		         String userName  = rs.getString("username");
		         users.add(userName);
	  	    }
	  	   }catch(SQLException se){
	  	      //Handle errors for JDBC
	  	      se.printStackTrace();
	  	   }catch(Exception e){
	  	      //Handle errors for Class.forName
	  	      e.printStackTrace();
	  	   }finally{
	  	      //finally block used to close resources
	  	      try{
	  	         if(stmt!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	      }// do nothing
	  	      try{
	  	         if(conn!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	         se.printStackTrace();
	  	      }//end finally try
	  	   }//end try
	  	   
		return users;
	  	}
	 
	 public static void insertUserBasicInfo(UserBasicInfo userInfo, String userName)
	 {
	     StringBuilder queryBuilder = null;
	      
	     if (userInfo.getFirstName() != null || userInfo.getLastName() != null || userInfo.getCountry() != null || userInfo.getCreditCardNumber() != null)
	     {
	    	 queryBuilder = new StringBuilder("UPDATE userinfo set ");
	    	 if (userInfo.getFirstName() != null)
	 	     {
	 	    	 queryBuilder.append(" firstname = '" + userInfo.getFirstName() + "', ");
	 	     }
	    	 if (userInfo.getLastName() != null)
	 	     {
	 	    	 queryBuilder.append(" lastname = '" + userInfo.getLastName() + "', ");
	 	     }
	    	 if (userInfo.getFirstName() != null)
	 	     {
	 	    	 queryBuilder.append(" country = '" + userInfo.getCountry() + "', ");
	 	     }
	    	 if (userInfo.getFirstName() != null)
	 	     {
	 	    	 queryBuilder.append(" creditCardNumber = '" + userInfo.getCreditCardNumber() + "', ");
	 	     }
	    	 
	    	 String queryTemp = queryBuilder.toString();
	    	 
	    	 int index = queryTemp.lastIndexOf(",");
	    	 queryTemp = new StringBuilder(queryTemp).replace(index, index+1,"").toString();
	    	 System.out.println(queryTemp);
	    	 
	    	 queryBuilder = new StringBuilder(queryTemp);
	    	 
	    	 queryBuilder.append(" where username = '" + userName + "'");
	     }
	   
	     
	     String sql = queryBuilder.toString();
	     System.out.println("Updat sql is " + sql);
		 
		
	  	   Connection conn = null;
	  	   Statement stmt = null;
	  	   try{
	  	      //STEP 2: Register JDBC driver
	  	      Class.forName("com.mysql.jdbc.Driver");

	  	      //STEP 3: Open a connection
	  	      //System.out.println("Connecting to a selected database...");
	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
	  	      //System.out.println("Connected database successfully...");
	  	      
	  	      //STEP 4: Execute a query
	  	      //System.out.println("Inserting records into the table...");
	  	      stmt = conn.createStatement();
	  	      PreparedStatement st;

	  	      st = conn.prepareStatement(sql);

	  	      //and then you can execute it
	  	      st.executeUpdate();
	  	      //System.out.println("Inserted records into the table...");

	  	   }catch(SQLException se){
	  	      //Handle errors for JDBC
	  	      se.printStackTrace();
	  	   }catch(Exception e){
	  	      //Handle errors for Class.forName
	  	      e.printStackTrace();
	  	   }finally{
	  	      //finally block used to close resources
	  	      try{
	  	         if(stmt!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	      }// do nothing
	  	      try{
	  	         if(conn!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	         se.printStackTrace();
	  	      }//end finally try
	  	   }//end try
	  	   System.out.println("User info updated...");
	  	   System.out.println("Goodbye!");
	  	}
	 
	 
	 /**
	 * Return user information
	 */
	 public static UserBasicInfoLinks getUserInfo(String username, String elementId) {
	  	   Connection conn = null;
	  	   Statement stmt = null;
	  	   List<String> users = new ArrayList<String>();
	  	   
	  	   UserBasicInfoLinks userInfo = new UserBasicInfoLinks();
	  	   ValueElement valueElement = null;
	  	   
	  	   try{
	  	      //STEP 2: Register JDBC driver
	  	      Class.forName("com.mysql.jdbc.Driver");

	  	      //STEP 3: Open a connection
	  	      //System.out.println("Connecting to a selected database...");
	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
	  	      //System.out.println("Connected database successfully...");
	  	      
	  	      //STEP 4: Execute a query
	  	      //System.out.println("Inserting records into the table...");
	  	      stmt = conn.createStatement();
	  	      PreparedStatement st;

	  	      String sql = "SELECT * from userinfo where username = '" + username + "'";
	  	      System.out.println(sql);
	  	      
	  	      //System.out.println(sql);
	  	      ResultSet rs = stmt.executeQuery(sql);

	  	    while(rs.next()){
	  	    	 String canonicalLink = null;
	  	    	 
	  	    	 if (elementId == null)
	  	    	 {
	  	    		//Retrieve by column name
			         String firstName  = rs.getString("firstname");
			         
			         canonicalLink = "/UsersManagement/rest/users/information/firstname/";
			         valueElement = new ValueElement(firstName, canonicalLink);
			         userInfo.setFirstName(valueElement);
			         
			         String lastName  = rs.getString("lastname");
			         canonicalLink = "/UsersManagement/rest/users/information/lastname/";
			         valueElement = new ValueElement(lastName, canonicalLink);
			         userInfo.setLastName(valueElement);
			         
			         String country	  = rs.getString("country");
			         canonicalLink = "/UsersManagement/rest/users/information/country/";
			         valueElement = new ValueElement(country, canonicalLink);
			         userInfo.setCountry(valueElement);
			         
			         String creditCardNumber  = rs.getString("creditCardNumber");
			         canonicalLink = "/UsersManagement/rest/users/information/creditcardnumber/";
			         valueElement = new ValueElement(creditCardNumber, canonicalLink);
			         userInfo.setCreditCardNumber(valueElement);
	  	    	 }
	  	    	 else if (elementId.equals("firstname"))
	  	    	 {
	  	    		 String firstName  = rs.getString("firstname");
	  	    		 if (firstName != null)
	  	    		 {
	  	    			 canonicalLink = "/UsersManagement/rest/users/information/firstname/";
				         valueElement = new ValueElement(firstName, canonicalLink);
				         userInfo.setFirstName(valueElement);
	  	    		 }
			        
	  	    	 }
	  	    	 else if (elementId.equals("lastname"))
	  	    	 {
	  	    		String lastName  = rs.getString("lastname");
			        if (lastName != null)
			        {
			        	canonicalLink = "/UsersManagement/rest/users/information/lastname/";
				         valueElement = new ValueElement(lastName, canonicalLink);
				         userInfo.setLastName(valueElement);
			        }
	  	    		
	  	    	 }
	  	    	 else if (elementId.equals("country"))
	  	    	 {
	  	    		 String country	  = rs.getString("country");
	  	    		 if (country != null)
	  	    		 {
	  	    			 canonicalLink = "/UsersManagement/rest/users/information/country/";
				         valueElement = new ValueElement(country, canonicalLink);
				         userInfo.setCountry(valueElement);
	  	    		 }
			        
	  	    	 }
	  	    	 else if (elementId.equals("creditcardnumber"))
	  	    	 {
	  	    		 String creditCardNumber  = rs.getString("creditCardNumber");
	  	    		 if (creditCardNumber != null)
	  	    		 {
	  	    			 canonicalLink = "/UsersManagement/rest/users/information/creditcardnumber/";
				         valueElement = new ValueElement(creditCardNumber, canonicalLink);
				         userInfo.setCreditCardNumber(valueElement);
	  	    		 }
			        
	  	    	 }
	  	    	 
	  	    }
	  	   }catch(SQLException se){
	  	      //Handle errors for JDBC
	  	      se.printStackTrace();
	  	   }catch(Exception e){
	  	      //Handle errors for Class.forName
	  	      e.printStackTrace();
	  	   }finally{
	  	      //finally block used to close resources
	  	      try{
	  	         if(stmt!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	      }// do nothing
	  	      try{
	  	         if(conn!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	         se.printStackTrace();
	  	      }//end finally try
	  	   }//end try
	  	   
		return userInfo;
	  	}
	 
	 
	 /**
	 * delete user info
	 */
	 public static void deleteUserInfo(String username, String elementId) {
		 StringBuilder queryBuilder = null;
	     
	     queryBuilder = new StringBuilder("UPDATE userinfo set ");
	     
	     if (elementId.equals("firstname"))
    	 {
	    	 queryBuilder.append(" firstname = NULL");
    	 }
    	 else if (elementId.equals("lastname"))
    	 {
    		 queryBuilder.append(" lastname = NULL");
    	 }
    	 else if (elementId.equals("country"))
    	 {
    		 queryBuilder.append(" country = NULL");
    	 }
    	 else if (elementId.equals("creditcardnumber"))
    	 {
    		 queryBuilder.append(" creditCardNumber = NULL");
    	 } 
	     
	    	 
	     queryBuilder.append(" where username = '" + username + "'");
	     
	   
	     String sql = queryBuilder.toString();
	     System.out.println("Updat sql is " + sql);
		 
		
	  	   Connection conn = null;
	  	   Statement stmt = null;
	  	   try{
	  	      //STEP 2: Register JDBC driver
	  	      Class.forName("com.mysql.jdbc.Driver");

	  	      //STEP 3: Open a connection
	  	      //System.out.println("Connecting to a selected database...");
	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
	  	      //System.out.println("Connected database successfully...");
	  	      
	  	      //STEP 4: Execute a query
	  	      //System.out.println("Inserting records into the table...");
	  	      stmt = conn.createStatement();
	  	      PreparedStatement st;

	  	      st = conn.prepareStatement(sql);

	  	      //and then you can execute it
	  	      st.executeUpdate();
	  	      //System.out.println("Inserted records into the table...");

	  	   }catch(SQLException se){
	  	      //Handle errors for JDBC
	  	      se.printStackTrace();
	  	   }catch(Exception e){
	  	      //Handle errors for Class.forName
	  	      e.printStackTrace();
	  	   }finally{
	  	      //finally block used to close resources
	  	      try{
	  	         if(stmt!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	      }// do nothing
	  	      try{
	  	         if(conn!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	         se.printStackTrace();
	  	      }//end finally try
	  	   }//end try
	  	   System.out.println("User info updated...");
	  	   System.out.println("Goodbye!");
	  	}
	 
	 public static void updateUserInfo(String username,  UserBasicInfo userInfo, String elementId) {
		 StringBuilder queryBuilder = null;
	     
	     queryBuilder = new StringBuilder("UPDATE userinfo set ");
	     
	     if (elementId.equals("firstname"))
    	 {
	    	 queryBuilder.append(" firstname = '" + userInfo.getFirstName() + "'");
    	 }
    	 else if (elementId.equals("lastname"))
    	 {
    		 queryBuilder.append(" lastname = '" + userInfo.getLastName() + "'");
    	 }
    	 else if (elementId.equals("country"))
    	 {
    		 queryBuilder.append(" country = '" + userInfo.getCountry() + "'");
    	 }
    	 else if (elementId.equals("creditcardnumber"))
    	 {
    		 queryBuilder.append(" creditCardNumber = '" + userInfo.getCreditCardNumber() + "'");
    	 } 
	     
	    	 
	     queryBuilder.append(" where username = '" + username + "'");
	     
	   
	     String sql = queryBuilder.toString();
	     System.out.println("Updat sql is " + sql);
		 
		
	  	   Connection conn = null;
	  	   Statement stmt = null;
	  	   try{
	  	      //STEP 2: Register JDBC driver
	  	      Class.forName("com.mysql.jdbc.Driver");

	  	      //STEP 3: Open a connection
	  	      //System.out.println("Connecting to a selected database...");
	  	      conn = DriverManager.getConnection(DB_URL+DB_NAME, USER, PASS);
	  	      //System.out.println("Connected database successfully...");
	  	      
	  	      //STEP 4: Execute a query
	  	      //System.out.println("Inserting records into the table...");
	  	      stmt = conn.createStatement();
	  	      PreparedStatement st;

	  	      st = conn.prepareStatement(sql);

	  	      //and then you can execute it
	  	      st.executeUpdate();
	  	      //System.out.println("Inserted records into the table...");

	  	   }catch(SQLException se){
	  	      //Handle errors for JDBC
	  	      se.printStackTrace();
	  	   }catch(Exception e){
	  	      //Handle errors for Class.forName
	  	      e.printStackTrace();
	  	   }finally{
	  	      //finally block used to close resources
	  	      try{
	  	         if(stmt!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	      }// do nothing
	  	      try{
	  	         if(conn!=null)
	  	            conn.close();
	  	      }catch(SQLException se){
	  	         se.printStackTrace();
	  	      }//end finally try
	  	   }//end try
	  	   System.out.println("User info updated...");
	  	   System.out.println("Goodbye!");
	  	}

	 
	 
}
	 