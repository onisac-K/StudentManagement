package com.onisac.mysql.operator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.onisac.mysql.model.*;

public class DBoperator implements DBconfig{
	public static String Check(String U,String P){
	   String stateOfCheck = new String("null");
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection、
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query、
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT username, password ,priority FROM tb_user";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         String username = rs.getString("username");
	         String password = rs.getString("password");
	         //System.out.println(username + " " + password + U + " " + P);
	         if(username.equals(U) && password.equals(P)){
	        	 stateOfCheck = new String(rs.getString("priority"));
	         }
	         //Display values
	         //System.out.println(id + " " + username + " " + password + " " + email);
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
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
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	   return stateOfCheck;
	}		
	public static void add(String U, String P, String E, String S) {   
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection、
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query、
	      stmt = conn.createStatement();
	      String sql;
	      sql = "INSERT INTO tb_user (username, password, email, priority) values "
	      		+ "(" + "'" + U + "'" + "," + "'" + P + "'" 
	    		  + "," + "'" + E + "'" + "," + "'" + S + "'"+ ")";
	      stmt.executeUpdate(sql);
	      
	      //STEP 5: Extract data from result set
	      
	      //STEP 6: Clean-up environment
	      stmt.close();
	      conn.close();
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
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   } 		
	}
}
