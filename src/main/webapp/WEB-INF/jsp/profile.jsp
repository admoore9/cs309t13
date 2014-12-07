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
                <h2>Profile</h2>
                
                <div id="accordion" class="panel-group">

                    <div id="join-tournament-panel" class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-target="#join-tournament-content" href="#join-tournament-content">
                                    Join a Tournament
                                </a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse" id="join-tournament-content">
                            <div class="panel-body">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Sport</th>
                                            <th>Registration Open</th>
                                            <th>Registration Close</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${tournaments}" var="tournament">
                                            <tr>
                                                <td><a href="<c:out value="/tournament/${tournament.id}/view"/>"><c:out value="${tournament.name}"/></a></td>
                                                <td>TO DO</td>
                                                <td>TO DO</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div id="my-teams-panel" class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-target="#my-teams-content" href="#my-teams-content">
                                    My Teams
                                </a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse" id="my-teams-content">
                            <div class="panel-body">
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
                                                <td><a href="<c:out value="/team/${team.id}/view"/>"><c:out value="${team.name}"/></a></td>
                                                <td><a href="<c:out value="/tournament/${team.tournament.id}/view"/>"><c:out value="${team.tournament.name}"/></a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    
                    <div id="my-invited-teams-panel" class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-target="#my-invited-teams-content" href="#my-invited-teams-content">
                                    Team Invites (${invitedTeamsSize})
                                </a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse" id="my-invited-teams-content">
                            <div class="panel-body">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Team Name</th>
                                            <th>Tournament</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${invitedTeams}" var="team">
                                            <tr>
                                                <td><a href="<c:out value="/team/${team.id}/view"/>"><c:out value="${team.name}"/></a></td>
                                                <td><a href="<c:out value="/tournament/${team.tournament.id}/view"/>"><c:out value="${team.tournament.name}"/></a></td>
                                                <td>
                                                <form role="form" id="invite-accept${team.id}-form" action="<c:out value="/team/${team.id}/addPlayer"/>" method="POST">
                                                    <button id="invite-accept-submit" type="submit" class="btn btn-default">Accept</button>
                                                </form>
                                                <form role="form" id="invite-reject${team.id}-form" action="<c:out value="/team/${team.id}/rejectInvite"/>" method="POST">
                                                    <button id="invite-reject-submit" type="submit" class="btn btn-default">Reject</button>
                                                </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div id="game-history-panel" class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-target="#game-history-content" href="#game-history-content">
                                    Game History
                                </a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse" id="game-history-content">
                            <div class="panel-body">
                            </div>
                        </div>
                    </div>

                    <div id="edit-profile-panel" class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-target="#edit-profile-content" href="#edit-profile-content">
                                    Edit Profile
                                </a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse" id="edit-profile-content">
                            <div class="panel-body">
                                <form role="form" id="edit-profile-form" action="/profile/edit" method="POST">
                                    <div class="form-group">
                                        <label for="name">Name:</label>
                                        <input type="text" class="form-control" id="name" name="name">
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Password:</label>
                                        <input id="password" name="password" type="password" class="form-control input-md">
                                    </div>
                                    <div class="form-group">
                                        <label for="sex">Sex:</label>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="sex" value="m" /> Male
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="sex" value="f" /> Female
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="height">Height:</label>
                                        <input id="height" name="height" type="text" placeholder="height in inches" class="form-control input-md">
                                    </div>
                                    <div class="form-group">
                                        <label for="weight">Weight:</label>
                                        <input id="weight" name="weight" type="text" placeholder="weight in pounds" class="form-control input-md">
                                    </div>
                                    <button id="create-tournament-submit" type="submit" class="btn btn-default">Submit</button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>

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
    <script src="../../resources/js/profile.js"></script>
    <script src="../../resources/js/header.js"></script>
</footer>
</html>
