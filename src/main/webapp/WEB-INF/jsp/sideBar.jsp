<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page import="javax.persistence.EnumType" %>
<%@ page import="edu.iastate.models.Member" %>
<% Member member = (Member) session.getAttribute("member"); %>

<div class="col-lg-3 col-md-4 hidden-sm hidden-xs" id="sidebar">
    <h2>My Next Games</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Tournament</th>
                <th>Team</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Tournament 1</td>
                <td>Team 1</td>
                <td>11/16/14 @9pm</td>
            </tr>
            <tr>
                <td>Tournament 2</td>
                <td>Team 2</td>
                <td>11/17/14 @9pm</td>
            </tr>
            <tr>
                <td>Tournament 1</td>
                <td>Team 1</td>
                <td>11/18/14 @9pm</td>
            </tr>
            <tr>
                <td>Tournament 2</td>
                <td>Team 2</td>
                <td>11/19/14 @9pm</td>
            </tr>
        </tbody>
    </table>

    <h2>Upcoming Tournaments</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Tournament</th>
                <th>Sign-up Start</th>
                <th>Sign-Up End</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Tournament 3</td>
                <td>11/20/14 @9pm</td>
                <td>11/26/14 @9pm</td>
            </tr>
            <tr>
                <td>Tournament 4</td>
                <td>11/24/14 @9pm</td>
                <td>11/28/14 @9pm</td>
            </tr>
            <tr>
                <td>Tournament 5</td>
                <td>11/26/14 @9pm</td>
                <td>12/4/14 @9pm</td>
            </tr>
            <tr>
                <td>Tournament 6</td>
                <td>11/26/14 @9pm</td>
                <td>12/4/14 @9pm</td>
            </tr>
        </tbody>
    </table>
    
    <h2>Upcoming Tournaments</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Tournament</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${tournaments}" var="tournament">
                <tr>
                    <td><a href="<c:out value="/tournament/${tournament.id}/view"/>"><c:out value="${tournament.name}"/></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>