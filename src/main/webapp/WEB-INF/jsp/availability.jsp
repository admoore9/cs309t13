<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>Game Page</title>

<style type="text/css">
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
    <table style="width: 100%">
        <tr>
            <th>Day</th>
            <th>Available Period</th>
        </tr>
        <c:forEach items="${days}" var="day">
            <tr>
                <td><c:out value="${day.getName()}" /></td>
                <td>
	                <c:forEach items="${day.getAvailablePeriods()}" var="period">
                      <c:out value="${period.getStartHour()}"></c:out>:<c:out value="${period.getStartMinute()}"></c:out> - <c:out value="${period.getEndHour()}"></c:out>:<c:out value="${period.getEndMinute()}"></c:out> <br>
	                </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>