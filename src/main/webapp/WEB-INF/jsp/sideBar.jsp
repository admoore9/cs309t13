<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page import="javax.persistence.EnumType" %>
<%@ page import="edu.iastate.models.Member" %>
<% Member member = (Member) session.getAttribute("member"); %>

<div class="col-lg-3 col-md-4 hidden-sm hidden-xs" id="sidebar">
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

    <h2>My Upcoming Games</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Team Name</th>
                <th>Tournament</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${teams}" var="team" >
                <tr>
                    <td><a href="<c:out value="/team/${team.id}/view"/>"><c:out value="${team.name}"/></a></td>
                    <td><a href="<c:out value="/tournament/${team.tournament.id}/view"/>"><c:out value="${team.tournament.name}"/></a></td>
                    <td>TO DO</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>