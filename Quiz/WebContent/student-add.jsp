<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<font color="red"><%=request.getAttribute("msg") %></font>
<form method="post" action="<%=request.getContextPath() %>/Controller">

<input type="hidden" name="action" value="student-form" />

Student ID: <input type="text" name="studentid" value="<%=request.getAttribute("studentid") %>"/> 
<br><br>
Student Name: <input type="text" name="studentname" value="<%=request.getAttribute("studentname") %>"/> 
<br><br>
Password: <input type="text" name="password" value="<%=request.getAttribute("password") %>"/>
<br><br>

<input type="submit" name="submit" value="Submit" />

</form>

<a href="<%=request.getContextPath() %>/Controller?action=admin-home">Back</a>
</body>
</html>