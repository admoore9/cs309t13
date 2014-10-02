<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<title>Game Page</title>
		
		<style>
			table, th, td {
			    border: 1px solid black;
			    border-collapse: collapse;
			}
			th, td {
			    padding: 5px;
			}
			th {
			    text-align: left;
			}
		</style>
	</head>

	<body>
	
		<table style="width:100%">
			<tr>
	    		<th>Game Id</th>
	    		<th>Game Location</th>		
	    		<th>Game Time</th>
			    <th>Tournament Id</th>
			    <th>Next Game Id</th>
	  		</tr>
	  	<c:forEach items="${games}" var="game">
		  	<tr>
			    <td><c:out value="${game.getId()}"/></td>
			    <td><c:out value="${game.getGameLocation()}"/></td>		
			    <td><c:out value="${game.getGameTime()}"/></td>
			    <td><c:out value="${game.getTournament()}"/></td>
			    <td></td>
		  	</tr>
		</c:forEach>
		</table>
	
	</body>
</html>