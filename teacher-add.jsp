<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add teacher</title>
</head>
<body>

<font color="red"><%=request.getAttribute("msg") %></font>

<form method="post" action="<%=request.getContextPath() %>/Controller">

<input type="hidden" name="action" value="teacher-form" />

Teacher ID: <input type="text" name="teacherid" value="<%=request.getAttribute("teacherid") %>"/> 
<br><br>
Teacher Name: <input type="text" name="teachername" value="<%=request.getAttribute("teachername") %>"/> 
<br><br>
Password: <input type="text" name="password" value="<%=request.getAttribute("password") %>"/>
<br><br>
Subject: <input type="text" name="subject" value="<%=request.getAttribute("subject") %>"/>
<br><br>

<input type="submit" name="submit" value="Submit" />
</form>
<a href="<%=request.getContextPath() %>/Controller?action=admin-home" style="text-decoration:none">Back</a>
</body>
</html>