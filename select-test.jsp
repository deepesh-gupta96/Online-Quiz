<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select</title>
</head>
<body>
<sql:setDataSource var="ds" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/quiz"
     user="root"  password=""/>
     
<sql:query dataSource="${ds }" var="result">
SELECT * FROM teacher;
</sql:query>

<table>

<tr>
<th>Subject Name</th>
<th>----</th>
</tr>

<c:forEach var="row" items="${result.rows }">

<tr>
<td><c:out value="${row.subject }" /> </td>
<td><a href="<%=request.getContextPath() %>/Controller?action=attempt-test&tid=<c:out value="${row.tid }"/>">Attempt Test</a></td>

</tr>

</c:forEach>

</table>
</body>
</html>