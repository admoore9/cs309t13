<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Members: </h1>

<table style="width:100%">
   <tr>
   	<td><c:out value="ID"/></td>
   	<td><c:out value="Name"/></td>
   	<td><c:out value="Is admin?"/></td>
   	<td><c:out value="Is official?"/></td>
   </tr>
<c:forEach items="${members}" var="member">
   <tr>
   	<td><c:out value="${member.getID()}"/></td>
   	<td><c:out value="${member.getName()}"/></td>
   	<td><c:out value="${member.isAdmin()}"/></td>
   	<td><c:out value="${member.isOfficial()}"/></td>
   </tr>
</c:forEach>
</table>

</body>
</html>
