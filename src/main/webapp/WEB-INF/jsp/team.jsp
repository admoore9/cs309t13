<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Teams</title>
</head>
<body>
<h1>All Teams: </h1>

<table style="width:100%">
   <tr>
   	<td><c:out value="ID"/></td>
   	<td><c:out value="Name"/></td>
   </tr>
<c:forEach items="${teams}" var="team">
   <tr>
   	<td><c:out value="${team.getId()}"/></td>
   	<td><c:out value="${team.getGameLocation()}"/></td>
   </tr>
</c:forEach>
</table>

</body>
</html>
