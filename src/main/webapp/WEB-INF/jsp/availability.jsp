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

    <!-- jQuery library -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        
	<script type="text/javascript" src="<c:url value='/resources/js/availability.js' />"></script>
	
	<!-- DataTable -->
	<style type="text/css" style="http://cdn.datatables.net/1.10.4/css/jquery.dataTables.min.css"></style>
	<script type="text/javascript" src="<c:url value='http://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js' />"></script>
	
	
</head>

<body>

<table id="myTable">
        <thead>
            <tr>
                <th>Day</th>
                <th>6PM</th>
                <th>7PM</th>
                <th>8PM</th>
                <th>9PM</th>
                <th>10PM</th>
                <th>11PM</th>
                <th>12PM</th>
            </tr>
        </thead>
 
        <tbody>
            <tr>
                <td>Monday</td>
                <td>
                	<input type="checkbox" name="6PM" value="true" checked>
                </td>
                <td>
                	<input type="checkbox" name="7PM" value="true">
                </td>
                <td>
                	<input type="checkbox" name="8PM" value="true">
                </td>
                <td>
                	<input type="checkbox" name="9PM" value="true">
                </td>
                <td>
                	<input type="checkbox" name="10PM" value="true">
                </td>
                <td>
                	<input type="checkbox" name="11PM" value="true">
                </td>
                <td>
                	<input type="checkbox" name="12PM" value="true">
                </td>
            </tr>
         </tbody>
</table>
<!-- 
    <table style="width: 100%">
    	<c:forEach items="${days}" var="day">
	        	<c:if test='${day.getName().equals("Monday") }'>
	        		<td></td>
	            	<th>Monday</th>
			        <tr>
		                <c:forEach items="${day.getAvailablePeriods()}" var="period">
	                      <c:out value="${period.getSlot()}"></c:out> <br>
		                </c:forEach>
		            </tr>
	            </c:if>
	            <c:if test='${day.getName().equals("Tuesday") }'>
	            <th>Tuesday</th>
	            </c:if>
	            <c:if test='${day.getName().equals("Wednesday") }'>
	            <th>Wednesday</th>
	            </c:if>
	            <c:if test='${day.getName().equals("Thursday") }'>
	            <th>Thursday</th>
	            </c:if>
	            <c:if test='${day.getName().equals("Friday") }'>
	            <th>Friday</th>
	            </c:if>
	            <c:if test='${day.getName().equals("Saturday") }'>
	            <th>Saturday</th>
	            </c:if>
	            <c:if test='${day.getName().equals("Sunday") }'>
	            <th>Sunday</th>
	            </c:if>
        </c:forEach>

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
                      <c:out value="${period.getSlot()}"></c:out> <br>
	                </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>

    </table>
-->
</body>

</html>