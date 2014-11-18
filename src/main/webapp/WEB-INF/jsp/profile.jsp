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

    <title>Profile</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"/>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-9 col-md-8 col-sm-12 col-xs-12">

                <!-- Page specific html -->
                <h2>My teams</h2>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Team Name</th>
                            <th>Tournament</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${teams}" var="team">
                            <tr>
                                <td><c:out value = "${team.name}"/></td>
                                <td><a href="<c:out value="/tournament/${team.tournament.id}/view"/>"><c:out value="${team.tournament.name}"/></a></td>
                            </tr>
                        </c:forEach>
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
            <jsp:include page="sideBar.jsp"/>
        </div>
    </div>
</body>
<footer>
    <!-- jQuery library -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

    <!-- Bootstrap JavaScript plug-ins -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

    <!-- Bootstrap validator -->
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>

    <!-- Page specific JS -->
    <script src="../../resources/js/login.js"></script>
</footer>
</html>
