<%@page import="com.beans.Question"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question</title>
</head>
<sql:setDataSource var="ds1" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/quiz"
     user="root"  password=""/>
     
<sql:query dataSource="${ds1 }" var="result">
SELECT * FROM <%=request.getAttribute("subject") %>;
</sql:query>
<% int cid1=1;%>
<form action="<%=request.getContextPath()%>/Controller">
<% String subject = (String)request.getAttribute("subject");
	String sid = (String)session.getAttribute("sid");
%>
<input type="hidden" name="action" value="submit-question" />

<% String choice = ""; %>

<c:forEach var="row" items="${result.rows }">

<% choice="choice"+Integer.toString(cid1);%>

<c:out value="${row.id }" /> <br>
Question:<c:out value="${row.question }" /><br>
<INPUT TYPE="radio" NAME="<%=choice %>" VALUE="a"><c:out value="${row.a }" /><br>
<INPUT TYPE="radio" NAME="<%=choice %>" VALUE="b"><c:out value="${row.b }" /><br>
<INPUT TYPE="radio" NAME="<%=choice %>" VALUE="c"><c:out value="${row.c }" /><br>
<INPUT TYPE="radio" NAME="<%=choice %>" VALUE="d"><c:out value="${row.d }" /><br>
<% cid1++;%>

</c:forEach>
<% String cid = Integer.toString(cid1);%>
<input type = "hidden" name="cid" value="<%=cid %>">
<input type = "hidden" name="gname" value = "<%=choice %>" />
<input type = "hidden" name="subject" value = "<%=request.getAttribute("subject") %>" />

<input type="submit" name="submit" value="Submit" />
</form>


</body>
</html>