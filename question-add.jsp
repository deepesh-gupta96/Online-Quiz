<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%=request.getAttribute("msg") %>
<form method="post" action="<%=request.getContextPath() %>/Controller">
<input type="hidden" name="action" value="question-form" />

Enter Question: <input type="text" name="question" /> 
<br><br>
Option No.1 <input type="text" name="option1" />
<br><br>
Option No.2 <input type="text" name="option2" />
<br><br>
Option No.3 <input type="text" name="option3" />
<br><br>
Option No.4 <input type="text" name="option4" />
<br><br>
Correct option : <input type="text" name="answer" />
<br><br>
<br><br>
<input type="submit" name="submit" value="Next Question" />
<br><br>
<a href="<%=request.getContextPath() %>/Controller?action=teacher-home">Submit</a>
<br><br>
</form>


</body>
</html>