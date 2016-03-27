package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Account {
	String user = "root"; 
	String password="";
	String url="jdbc:mysql://localhost:3306/";
	String dbname="quiz"; 
	String driver="com.mysql.jdbc.Driver";

	Connection con;
	
	public boolean checkTeacher(String tid, String password) throws Exception{
		
		dbConnect();
		
		int count = 0; 
		String sql = "select count(*) as count from teacher where tid = ? AND password = ?"; 
		PreparedStatement pstmt = con.prepareStatement(sql); 
		pstmt.setString(1, tid); 
		pstmt.setString(2, password); 
		ResultSet rst = pstmt.executeQuery(); 

		while(rst.next()){
			count = rst.getInt("count");
		} 
		dbClose(); 

		if(count == 0) 
			return false; 

		else 
			return true;
	}
	
	public boolean checkStudent(String sid, String password) throws Exception{
		
		dbConnect();		
		int count = 0; 
		String sql = "select count(*) as count from student where sid = ? AND password = ?"; 
		PreparedStatement pstmt = con.prepareStatement(sql); 
		pstmt.setString(1, sid); 
		pstmt.setString(2, password); 
		ResultSet rst = pstmt.executeQuery(); 

		while(rst.next()){
			count = rst.getInt("count");
		} 
		dbClose(); 
		if(count == 0) 
			return false; 

		else 
			return true;
	}

	public void insertTeacher(String tid, String teachername, String password, String subject) throws Exception{
		
		dbConnect(); 
		String sql = "insert into teacher(tid,name,password,subject) values(?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		String sql1 = "create table "+subject+"(id int NOT NULL AUTO_INCREMENT, question varchar(500), a varchar(500), b varchar(500), c varchar(500), d varchar(500), correct varchar(1), PRIMARY KEY(id))";
		PreparedStatement pstmt1 = con.prepareStatement(sql1);
		pstmt.setString(1,tid);
		pstmt.setString(2,teachername); 
		pstmt.setString(3,password); 
		pstmt.setString(4,subject);
		pstmt1.executeUpdate();
		pstmt.executeUpdate();
		dbClose();
	}
	
	public void insertStudent(String sid, String studentname, String password) throws Exception{
		
		dbConnect(); 
		String sql = "insert into student(sid,name,password) values(?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,sid);
		pstmt.setString(2,studentname); 
		pstmt.setString(3,password); 
		pstmt.executeUpdate();
		dbClose();
	}
	
	public void insertQuestion(String question, String option1, String option2, String option3, String option4, String tid, String answer) throws Exception {
		
		dbConnect();
		String sql = "select subject from teacher where tid="+tid;
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		String subject = "";
		while(rst.next()){
			subject = rst.getString(1);
		}
		String sql1 = "insert into "+subject+" (question,a,b,c,d,correct) values(?,?,?,?,?,?)";
		PreparedStatement pstmt1 = con.prepareStatement(sql1); 
		pstmt1.setString(1,question); 
		pstmt1.setString(2,option1); 
		pstmt1.setString(3,option2);
		pstmt1.setString(4,option3); 
		pstmt1.setString(5,option4);
		pstmt1.setString(6,answer); 
		pstmt1.executeUpdate();
		dbClose(); 
	}
	
	public String getTest(String tid) throws Exception{
		dbConnect();
		String sql = "select subject from teacher where tid="+tid;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rst = stmt.executeQuery();
		String subject = "";
		while(rst.next()){
			subject = rst.getString(1);
		}
		dbClose();
		return subject;
	}
	
	public void checkAttempt() throws Exception{
		dbConnect();
		
		dbClose();
	}
	
	
	
	public void checkResultTeacher() throws Exception{
		dbConnect();
		
		dbClose();
	}
	
	public void checkResultStudent() throws Exception{
		dbConnect();
		
		dbClose();
	}
	
	public void calculateResult(HashMap<Integer, String> map1, String subject, String sid) throws Exception{
		dbConnect();
		String sql="select correct from "+subject;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rst = stmt.executeQuery();
		int i=1;
		System.out.println(map1);
		HashMap <Integer,String> map2 = new HashMap<Integer,String>();
		while(rst.next()){
			String correct=rst.getString("correct");
			map2.put(i, correct);
			i++;
		}
	    String [] choice=new String[i-1];
	    String [] correct=new String[i-1];
	    int j;
	    i=i-1;
	    for(j=1;j<=i;j++){
	    	choice[j-1]=(String)map1.get(j);
	    	System.out.println(choice[j-1]);
	    	correct[j-1]=(String)map2.get(j);
	    }
		int marks=0;
		for(j=0;j<i;j++){
			System.out.println(choice[j]+"   "+correct[j]);
		    if(choice[j].equals(correct[j])){
		    	marks++;
		    }
		}
		String mark = Integer.toString(marks);
		String sql2="insert into testattempt (sid,name,tid,status,result) values(?,?,?,?,?)";
		String sql3="select name from student where sid="+sid;
		PreparedStatement pstmt3 = con.prepareStatement(sql3);
		ResultSet rst3 = pstmt3.executeQuery();
		String name="",tid = "";
		while(rst3.next()){
			name = rst3.getString(1);
		}
		
		String sql4="select tid from teacher where subject='"+subject+"'";
		PreparedStatement pstmt4 = con.prepareStatement(sql4);
		ResultSet rst4 = pstmt4.executeQuery();
		while(rst4.next()){
			tid = rst4.getString(1);
		}
		
		PreparedStatement pstmt2 = con.prepareStatement(sql2);
		pstmt2.setString(1, sid);
		pstmt2.setString(2, name);
		pstmt2.setString(3, tid);
		pstmt2.setString(4, "1");
		pstmt2.setString(5, mark);
		pstmt2.executeUpdate();
		
		dbClose();
	}
	

	public void dbConnect() throws ClassNotFoundException, SQLException{
		
		Class.forName(driver);
	    con = DriverManager.getConnection(url+dbname,user,password); 
	} 
	
	public void dbClose() throws SQLException{ 
		con.close();
	}
	public void test() throws Exception{
		dbConnect();
		
	}

}


