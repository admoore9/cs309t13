<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page import="javax.persistence.EnumType" %>
<%@ page import="edu.iastate.models.Member" %>
<% Member member = (Member) session.getAttribute("member"); %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Tournament</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"/>

    <!-- Page specific CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bracket.css"/>">
</head>
<body data-tournament-id="<c:out value='${tournament.id}'/>" data-tournament-formed="<c:out value='${tournament.isBracketFormed()}'/>" data-at-least-official="<c:out value='${!(userType == \'PLAYER\')}'/>">
    <jsp:include page="header.jsp"/>

    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-9 col-md-8 col-sm-12 col-xs-12">
                <h2>Bracket</h2>
                <h3>Tournament Name: ${tournament.name}</h3>
                <div id="bracket"></div>
                <br>
                <c:choose>
                    <c:when test="${tournament.isBracketFormed()}">
                        <div id="bracket"></div>
                    </c:when>
                    <c:otherwise>
                        <strong>The bracket for this tournament isn't formed yet.<br/></strong>
                        <br/><br/>

                        <c:if test="${userType == 'ADMIN' || userType == 'COORDINATOR'}">
                            <div class="btn btn-primary" id="form-bracket">Form bracket</div>
                        </c:if>

                        <a href="#" class="btn btn-primary btn-primary" id="createTeam">Create Team</a>
                        <a href="#" class="btn btn-primary btn-primary" id="joinTeam">Join Team</a>
                    </c:otherwise>
                </c:choose>

            </div>
            <jsp:include page="sideBar.jsp"/>
        </div>
    </div>
</body>
<footer>
    <!-- jQuery library -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

    <!-- Bootstrap JavaScript plug-ins -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

    <!-- Page specific JS -->
    <script src="../../resources/js/tournament.js"></script>
</footer>
</html>
