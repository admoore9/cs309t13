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

    <title>Join Team</title>

    <!-- CSS -->
    <link rel="stylesheet" href="../../resources/js/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"/>

    <!-- Page specific CSS -->
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-9 col-md-8 col-sm-12 col-xs-12">

                <!-- Page specific html -->
                <h2>Tournament ${tournament.name} Teams</h2>
                
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Team Name</th>
                            <th>Skill Level</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${tournament.teams}" var="team">
                            <tr>
                                <td><a href="<c:out value="/team/${team.id}/view"/>"><c:out value="${team.name}"/></a></td>
                                <td>${team.teamSkillLevel}</td>
                                <td>
                                <form role="form" id="join-team-form">
                                    <div class="form-group">
                                        <label for="name">Team Password: </label>
                                        <input type="text" class="form-control" id="teampassword" name="teamPassword">
                                    </div>
                                    <button id="invite-accept-submit" type="submit" class="btn btn-default">Join</button>
                                    <script type="text/javascript">
                                        var teamId = '<c:out value="${team.id}"/>';
                                    </script>
                                </form>
                                </td>
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
    <script src="../../resources/js/bootstrap/js/bootstrap.min.js"></script>

    <!-- Bootstrap validator (if needed) -->
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>

    <!-- Page specific JS -->
    <script src="../../resources/js/joinTeam.js"></script>
</footer>
</html>