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
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script type="text/javascript"
	src="<c:url value='/resources/js/availability.js' />"></script>

<!-- DataTable -->
<style type="text/css"
	style="http://cdn.datatables.net/1.10.4/css/jquery.dataTables.min.css"></style>
<script type="text/javascript"
	src="<c:url value='http://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js' />"></script>


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
			<c:forEach items="${days}" var="day">
				<tr>
					<td><c:out value="${day.getName()}"></c:out></td>

					<c:forEach items="${day.getAvailablePeriods()}" var="period">
						<td><input type="checkbox"
							name="<c:out value='${day.getName()}'></c:out>"
							value="SIX"
							<c:if test='${period.getSlot() == "SIX" }'>checked</c:if>>
						</td>
						<td><input type="checkbox"
							name="<c:out value='${day.getName()}'></c:out>"
							value="SEVEN"
							<c:if test='${period.getSlot() == "SEVEN" }'>checked</c:if>>
						</td>
						<td><input type="checkbox"
							name="<c:out value='${day.getName()}'></c:out>"
							value="EIGHT"
							<c:if test='${period.getSlot() == "EIGHT" }'>checked</c:if>>
						</td>
						<td><input type="checkbox"
							name="<c:out value='${day.getName()}'></c:out>"
							value="NINE"
							<c:if test='${period.getSlot() == "NINE" }'>checked</c:if>>
						</td>
						<td><input type="checkbox"
							name="<c:out value='${day.getName()}'></c:out>"
							value="TEN"
							<c:if test='${period.getSlot() == "TEN" }'>checked</c:if>>
						</td>
						<td><input type="checkbox"
							name="<c:out value='${day.getName()}'></c:out>"
							value="ELEVEN"
							<c:if test='${period.getSlot() == "ELEVEN" }'>checked</c:if>>
						</td>
						<td><input type="checkbox"
							name="<c:out value='${day.getName()}'></c:out>"
							value="TWELVE"
							<c:if test='${period.getSlot() == "TWELVE" }'>checked</c:if>>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<button id="submit" type="submit">Save</button>

</body>

</html>