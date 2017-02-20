package com.onisac.mysql.operator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.onisac.login.*;
import com.onisac.mysql.model.DBconfigOfStudent;

public class DBoperatorOfStudent implements DBconfigOfStudent{
	public static String select(String C){
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
	      sql = "select cno,tname from C";
	      //stmt.executeUpdate(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      while(rs.next()){
	         String cno = rs.getString("cno");
	         if(cno.equals(C)){
	        	 //System.out.print(cno + " " + tname);
	        	 String answer = insert(recent_user.user,C);
	        	 return answer;
	         }
	      }
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
	   return "null";
	}
	static String insert(String S,String C){
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
	      //stmt.executeUpdate(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      while(rs.next()){
	         String cno = rs.getString("cno");
	         String sno = rs.getString("sno");
	         
	         if(cno.equals(C) && sno.equals(S)){
	        	 //System.out.print(cno + " " + tname);
	        	 stmt.close();
	        	 conn.close();
	        	 stmt.close();
	        	 return "twice";
	         }
	      }
	      sql = "INSERT INTO sc (sno, cno,grade,pgrade,egrade) values "
	      		+ "(" + "'" + S + "'" + "," + "'" + C + "'" 
	    		  + "," + "'-1'" + "," + "'-1'"+"," + "'-1'" + ")";
	      stmt.executeUpdate(sql);
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
	   return "ok";
	}
	public static JTable query_course(String user) {
		String[] head = {"课程号","课程名","学分","专业","老师"};
		DefaultTableModel model = new DefaultTableModel(null, head);
		JTable table = new JTable(model);
	   //answer += "\n\r\n\r\n\r\n\r\n\r\n\r\n\r";
	  
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
	      sql = "select cno,cname,credit,cdept,tname from C where cno in "
	      		+ "(select cno from SC where sno = " + "'" + user+ "'"
	      		+ ")";
	      ResultSet rs = stmt.executeQuery(sql);
	      while(rs.next()){
	         String[] ary = new String[5];
	         ary[0] = rs.getString("cno");
	         ary[1] = rs.getString("cname");
	         ary[2] = String.valueOf(rs.getInt("credit"));
	         ary[3] = rs.getString("cdept");
	         ary[4] = rs.getString("tname");
	         model.addRow(ary);	 
	      }
	      table.invalidate();
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
	public static String getName(String user) {
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
	      sql = "select sno,sname from S";
	      //stmt.executeUpdate(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      while(rs.next()){
	         String sno = rs.getString("sno");
	         if(sno.equals(user)){
	        	 return rs.getString("sname");
	         }
	      }
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
	   return null;
	}
	public static JTable query_grade(String user) {
		String[] head = {"课程号","课程名","考试成绩","平时成绩","总评成绩"};
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
		      sql = "select C.cno,cname,grade,pgrade,egrade from C,SC where C.cno = SC.cno and sno =  "
		      		 + "'" + user+ "'";
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		         String cno = rs.getString("C.cno");
		         String cname = rs.getString("cname");
		         String grade = rs.getString("grade");
		         String pgrade = rs.getString("pgrade");
		         String egrade = rs.getString("egrade");
		         if(grade.equals("-1"))grade = "null";
		         if(pgrade.equals("-1"))pgrade = "null";
		         if(egrade.equals("-1"))egrade = "null";
		         String[] ary = new String[5];
		         ary[0] = cno;
		         ary[1] = cname;
		         ary[2] = grade;
		         ary[3] = pgrade;
		         ary[4] = egrade;
		         
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

	@SuppressWarnings("unchecked")
	public static JTable query_other_course(String user) {
		   String[] head = {"课程号","课程名","学分","专业","老师"};
		   DefaultTableModel model = new DefaultTableModel(null, head);
		   JTable table = new JTable(model);
		   @SuppressWarnings("rawtypes")
		   Set s=new HashSet(); 
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
		      sql = "select cno,cname,credit,cdept,tname from C where cno in "
		      		+ "(select cno from SC where sno = " + "'" + user+ "'"
		      		+ ")";
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		         String cno = rs.getString("cno");
		         s.add(cno);   	 
		      }
		      sql = "select cno,cname,credit,cdept,tname from C";
		      rs = stmt.executeQuery(sql);
		      while(rs.next()){
		    	  String[] ary = new String[5];
		    	  
			      ary[0] = rs.getString("cno");
			      ary[1] = rs.getString("cname");
			      ary[2] = String.valueOf(rs.getInt("credit"));
			      ary[3] = rs.getString("cdept");
			      ary[4] = rs.getString("tname");
		         if(!s.contains(ary[0])){
		        	 model.addRow(ary);
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
		   return table;	   
	}
	public static void cancel_course(String cno, String sno) {
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
	@SuppressWarnings("unchecked")
	public static String[][] query_other_speacial_course(String user, String cno, String cname, String credit,
			String cdept, String tname) {
	   //System.out.println(tname);
	   int cnt = 0;
	   
	   @SuppressWarnings("rawtypes")
	   Set s=new HashSet(); 
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
	      sql = "select cno,cname,credit,cdept,tname from C where cno in "
	      		+ "(select cno from SC where sno = " + "'" + user+ "'"
	      		+ ")";
	      ResultSet rs = stmt.executeQuery(sql);
	      while(rs.next()){
	         String Cno = rs.getString("cno");
	         s.add(Cno);   	 
	      }
	      sql = "select cno,cname,credit,cdept,tname from C";
	      rs = stmt.executeQuery(sql);
	      while(rs.next()){
	    	  String[] ary = new String[5];
	    	  
		      ary[0] = rs.getString("cno");
		      ary[1] = rs.getString("cname");
		      ary[2] = String.valueOf(rs.getInt("credit"));
		      ary[3] = rs.getString("cdept");
		      ary[4] = rs.getString("tname");
	         if(!s.contains(ary[0])){
	        	 if((cno.isEmpty() || cno.equals(ary[0])) && (cname.isEmpty() || cname.equals(ary[1]))
	        			 && (credit.isEmpty()  || credit.equals(ary[2])) && (cdept.isEmpty() || cdept.equals(ary[3]))
	        			 && (tname.isEmpty()  || tname.equals(ary[4]))){
	        		 cnt++;
	        	 }
	         }    
	     }
	      
	      answer = new String[cnt][5];
	      cnt = 0;
	      rs = stmt.executeQuery(sql);
	      while(rs.next()){
	    	  String[] ary = new String[5];
	    	  
		      ary[0] = rs.getString("cno");
		      ary[1] = rs.getString("cname");
		      ary[2] = String.valueOf(rs.getInt("credit"));
		      ary[3] = rs.getString("cdept");
		      ary[4] = rs.getString("tname");
	         if(!s.contains(ary[0])){
	        	 if((cno.isEmpty() || cno.equals(ary[0])) && (cname.isEmpty() || cname.equals(ary[1]))
	        			 && (credit.isEmpty()  || credit.equals(ary[2])) && (cdept.isEmpty() || cdept.equals(ary[3]))
	        			 && (tname.isEmpty()  || tname.equals(ary[4]))){
	        		 //System.out.println(ary[4]);
	        		 answer[cnt][0] = ary[0];
	        		 answer[cnt][1] = ary[1];
	        		 answer[cnt][2] = ary[2];
	        		 answer[cnt][3] = ary[3];
	        		 answer[cnt][4] = ary[4];
	        		 cnt++;
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
	
	
}
