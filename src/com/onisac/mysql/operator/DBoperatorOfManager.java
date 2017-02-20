package com.onisac.mysql.operator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.onisac.mysql.model.DBconfigOfStudent;


public class DBoperatorOfManager implements DBconfigOfStudent{
	public static JTable query_student() {
		   String[] head = {"学号","姓名","年龄","性别","专业"};
		   DefaultTableModel model = new DefaultTableModel(null, head);
		   JTable table = new JTable(model);
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
		      sql = "select sno,sname,age,sex,sdept from S";
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		    	  String[] ary = new String[5];
			      ary[0] = rs.getString("sno");
			      ary[1] = rs.getString("sname");
			      ary[2] = String.valueOf(rs.getInt("age"));
			      ary[3] = rs.getString("sex");
			      ary[4] = rs.getString("sdept");
			      model.addRow(ary);	
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
		   return table;	
	}
	public static boolean exitOfStudent(String SNO) {	
	   boolean flag = false;
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
	      sql = "select sno from S";
	      ResultSet rs = stmt.executeQuery(sql);
	      while(rs.next()){
	         String sno = rs.getString("sno");
	         if(sno.equals(SNO))flag = true;      	 
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
	   return flag;
	}
	public static void insert_inf_student(String sno, String sname, int age, String sex, String sdept) {
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
		      sql = "INSERT INTO s (sno, sname, age, sex, sdept) values "
			      		+ "(" + "'" + sno + "'" + "," + "'" + sname + "'" 
			    		  + "," + String.valueOf(age) + "," + "'" + sex 
			    		  + "'" + "," + "'" + sdept + "'" + ")";
		      stmt.executeUpdate(sql);
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
	public static void update_inf_student(String sno, String sname, int age, String sex, String sdept) {
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
	      sql = "UPDATE S set sname = " + "'" + sname + "',"
	    		 + "age = " + String.valueOf(age) + ","
	    		 + "sex = " + "'" + sex + "'," 
	    		 + "sdept = " + "'" + sdept + "'"
	    		 + "where sno = " + "'" + sno + "'";
	      stmt.executeUpdate(sql);
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
	public static boolean exitinSC(String sno, String cno) {
	   boolean flag = false;
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
	      sql = "select sno,cno from SC";
	      ResultSet rs = stmt.executeQuery(sql);
	      while(rs.next()){
	         String SNO = rs.getString("sno");
	         String CNO = rs.getString("cno");
	         if(sno.equals(SNO) && cno.equals(CNO))flag = true;      	 
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
	   return flag;
	}
	public static void update_infInSC_student(String sno, String cno, float grade, 
			float pgrade,float egrade) {
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
		      sql = "delete from SC where cno =" + "'" + cno + "'" + "and sno = " + "'" + sno + "'";
		      stmt.executeUpdate(sql);
		      sql = "INSERT INTO sc (sno, cno,grade,pgrade,egrade) values "
			      		+ "(" + "'" + sno + "'" + "," + "'" + cno + "'" 
			    		+ "," + String.valueOf(grade)  + "," + String.valueOf(pgrade)  + 
			    		"," + String.valueOf(egrade) + ")";
		      stmt.executeUpdate(sql);
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
	public static JTable query_gradeOfstudent() {
		   String[] head = { "学号","姓名","课号","考试成绩","平时成绩","总评成绩"};
		   DefaultTableModel model = new DefaultTableModel(null, head);
		   JTable table = new JTable(model);
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
		      sql = "select SC.sno,sname,cno,grade,pgrade,egrade from SC,S where SC.sno = S.sno";
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		    	  String[] ary = new String[6];
			      ary[0] = rs.getString("SC.sno");
			      ary[1] = rs.getString("sname");
			      ary[2] = rs.getString("cno");
			      ary[3] = String.valueOf(rs.getFloat("grade"));
			      ary[4] = String.valueOf(rs.getFloat("pgrade"));
			      ary[5] = String.valueOf(rs.getFloat("egrade"));	
			      for(int i=3;i<=5;++i){
			    	  if(ary[i].equals("-1.0")){
			    		  ary[i] = new String("null");
			    	  }
			      }
		          model.addRow(ary);
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
		   return table;	
	}
	
	public static String[][] average() {
		   Connection conn = null;
		   Statement stmt = null;
		   String[][] answer = null; 
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection、
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query、
		      stmt = conn.createStatement();
		      String sql;
		      int cnt = 0;
		      sql = "select cno, AVG(grade) as cj1,AVG(pgrade) as cj2,"
		      		+ "AVG(egrade) as cj3 from SC group by cno";
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		         cnt ++;
		      }
		      answer = new String[cnt][4];
		      rs = stmt.executeQuery(sql);
		      cnt = 0;
		      while(rs.next()){
		         String CNO = rs.getString("cno");
		         String Cname = GetCourseName(CNO);
		         String CJ1 = String.valueOf(rs.getFloat("cj1"));
		         String CJ2 = String.valueOf(rs.getFloat("cj2"));
		         String CJ3 = String.valueOf(rs.getFloat("cj3"));
		         answer[cnt][0] = Cname;
		         answer[cnt][1] = CJ1;
		         answer[cnt][2] = CJ2;
		         answer[cnt][3] = CJ3;
		         cnt ++;
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
		return answer;
	}
	private static String GetCourseName(String cNO) {
	   
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
	      sql = "select cno, cname from C";
	      ResultSet rs = stmt.executeQuery(sql);
	      while(rs.next()){
	         String cno = rs.getString("cno");
	         String cname = rs.getString("cname");
	         if(cno.equals(cNO)){
	        	 return cname;
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
	   return null;
	}
}
