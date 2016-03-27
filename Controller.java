package com.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.User;
import com.model.Account;


@WebServlet("/Controller")

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action"); //null
		
		if(action == null){
		request.setAttribute("msg", "");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else{
			doPost(request,response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		HttpSession session = request.getSession();
		if(action.equals("admin")){
			
			request.setAttribute("msg", "");
			request.setAttribute("username", "");
			request.setAttribute("password", "");
		
			request.getRequestDispatcher("admin-login.jsp").forward(request, response);
			
		}
		
		if(action.equals("teacher")){
			
			request.setAttribute("msg", "");
			request.setAttribute("tid", "");
			request.setAttribute("password", "");
		
			request.getRequestDispatcher("teacher-login.jsp").forward(request, response);
		}
		
		if(action.equals("student")){
			
			request.setAttribute("msg", "");
			request.setAttribute("sid", "");
			request.setAttribute("password", "");
		
			request.getRequestDispatcher("student-login.jsp").forward(request, response);
		}
		if(action.equals("admin-login")){ 
			
			String username =request.getParameter("username"); 
			String password = request.getParameter("password"); 
			
			User user = new User();
			
			boolean status =user.validate(username,password); 

			if(status == true){ 
				session.setAttribute("username", username);
				request.getRequestDispatcher("admin-home.jsp").forward(request, response); 

			} 

			else{ 

				request.setAttribute("username", username); 
				request.setAttribute("password", password); 
				request.setAttribute("msg", "Invalid Username/Password"); 
				request.getRequestDispatcher("admin-login.jsp").forward(request, response); 
			} 
		}
		if(action.equals("student-login")){ 

			String sid = request.getParameter("sid"); 
			String password = request.getParameter("password"); 
			
			Account account = new Account(); 

			boolean status=false;
			try {
				status = account.checkStudent(sid, password);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if(status == true){ 
				session.setAttribute("sid", sid);
				request.getRequestDispatcher("student-home.jsp").forward(request, response); 

			} 

			else{ 
				request.setAttribute("sid", sid); 
				request.setAttribute("password", password); 
				request.setAttribute("msg", "Invalid Username/Password"); 
				request.getRequestDispatcher("student-login.jsp").forward(request, response); 
			} 

		}
		if(action.equals("teacher-login")){ 

			String tid = request.getParameter("tid"); 
			String password = request.getParameter("password"); 
			
			boolean status = false;
			Account account = new Account(); 
			
			try {
				status = account.checkTeacher(tid, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(status == true){ 
				
				request.setAttribute("msg", "");
				session.setAttribute("tid", tid);
				
				request.getRequestDispatcher("teacher-home.jsp").forward(request, response); 
			} 

			else{ 

				request.setAttribute("username", tid); 
				request.setAttribute("password", password); 
				request.setAttribute("msg", "Invalid Username/Password"); 
				request.getRequestDispatcher("teacher-login.jsp").forward(request, response); 
			} 

		}
		
		if(action.equals("student-form")){

			String sid = request.getParameter("studentid");
			String studentname = request.getParameter("studentname");
			String password = request.getParameter("password");
			Account account = new Account();
			
			try {
				account.insertStudent(sid, studentname, password);
				request.setAttribute("studentid", "");
				request.setAttribute("studentname", "");
				request.setAttribute("password", "");
				request.setAttribute("msg", "Student added successfully!!!");
				request.getRequestDispatcher("student-add.jsp").forward(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		if(action.equals("teacher-form")){

			String tid = request.getParameter("teacherid");
			String teachername = request.getParameter("teachername");
			String password = request.getParameter("password");
			String subject = request.getParameter("subject");
			Account account = new Account();
			
			try {
				account.insertTeacher(tid, teachername, password, subject);
				request.setAttribute("teacherid", "");
				request.setAttribute("teachername", "");
				request.setAttribute("password", "");
				request.setAttribute("subject", "");
				request.setAttribute("msg", "Teacher added successfully!!");
				request.getRequestDispatcher("teacher-add.jsp").forward(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		if(action.equals("student-add")){

			request.setAttribute("msg", "");
			request.setAttribute("studentid", "");
			request.setAttribute("studentname", "");
			request.setAttribute("password", "");
			request.getRequestDispatcher("student-add.jsp").forward(request, response);
		}
		if(action.equals("teacher-add")){
			request.setAttribute("msg", "");
			request.setAttribute("teacherid", "");
			request.setAttribute("teachername", "");
			request.setAttribute("password", "");
			request.setAttribute("subject", "");
		
		
			request.getRequestDispatcher("teacher-add.jsp").forward(request, response);
		}
		if(action.equals("admin-home")){
			request.getRequestDispatcher("admin-home.jsp").forward(request, response);
		}
		if(action.equals("question-form")){
			request.setAttribute("msg", "");
			String question=request.getParameter("question");
			String option1=request.getParameter("option1");
			String option2=request.getParameter("option2");
			String option3=request.getParameter("option3");
			String option4=request.getParameter("option4");
			String answer=request.getParameter("answer");
			String tid = (String) session.getAttribute("tid");
			Account account = new Account();

			if(question=="" && option1=="" && option2=="" && answer=="" ){
				request.setAttribute("msg","fields are blank!");
				request.getRequestDispatcher("question-add.jsp").forward(request, response); 
			}
			else{
				try {
					account.insertQuestion(question,option1,option2,option3,option4,tid,answer);
					request.setAttribute("msg","question submitted!");
					request.getRequestDispatcher("question-add.jsp").forward(request, response); 
				}
				catch (Exception e) {

					request.getRequestDispatcher("question-add.jsp").forward(request, response); 
		
				}
			}
		}
		if(action.equals("teacher-home")){
			request.setAttribute("msg", "");
			request.getRequestDispatcher("teacher-home.jsp").forward(request, response);
		
		}
		if(action.equals("question-add")){
			request.setAttribute("msg", "");
			request.setAttribute("question", "");
			request.setAttribute("option1", "");
			request.setAttribute("option2", "");
			request.setAttribute("option3", "");
			request.setAttribute("option4", "");
			request.setAttribute("answer", "");
			request.getRequestDispatcher("question-add.jsp").forward(request, response);
		
		}
		
		if(action.equals("select-test")){
			request.getRequestDispatcher("select-test.jsp").forward(request, response);
		}
		
		if(action.equals("attempt-test")){
			
			String tid = request.getParameter("tid");
			Account acc = new Account();
			String subject = "";
			try {
				subject = acc.getTest(tid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("subject", subject);
			request.getRequestDispatcher("attempt-question.jsp").forward(request, response);
		
		}
		if(action.equals("student-result")){
			
			request.getRequestDispatcher("student-result.jsp").forward(request, response);
		
		}
		HashMap<Integer,String> map=new HashMap<Integer,String>();
		
		if(action.equals("submit-question")){
			String sid=(String)session.getAttribute("sid");
			String cid1 = request.getParameter("cid");
			int cid = Integer.parseInt(cid1);
			cid=cid-1;
			String subject = (String)request.getParameter("subject");
			String q = "choice";
			System.out.println(request.getParameter("subject")+"\n");

			for(int i = 1; i<=cid; i++){
				String gname = Integer.toString(i);
				if(request.getParameter(q+gname).equals("a")) {
					System.out.println(request.getParameter(q+gname)); 
	                map.put(i, "a");
	            }
	            if(request.getParameter(q+gname).equals("b")) {
	                System.out.println(request.getParameter(q+gname)); 
	                map.put(i, "b");
	 	                 
	            }
	            if(request.getParameter(q+gname).equals("c")) {
	                System.out.println(request.getParameter(q+gname)); 
	                map.put(i, "c");
	 	                
	            }
	            if(request.getParameter(q+gname).equals("d")) {
	                System.out.println(request.getParameter(q+gname)); 
	                map.put(i, "d");
	 	                
	            }
	                
	            
			}
			Account account = new Account();
			try {
				account.calculateResult(map, subject, sid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
		

}
	


