<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<%@ page import="javax.persistence.EnumType"%>
<%@ page import="edu.iastate.models.Member"%>
<%@ page import="edu.iastate.models.Team"%>
<%  Member member = (Member) session.getAttribute("member"); %>
<%  Team team = (Team) request.getAttribute("team");%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Team Page</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" />

    <!-- Page specific CSS -->
</head>
<body>
    <jsp:include page="header.jsp" />
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-9 col-md-8 col-sm-12 col-xs-12">
                <!-- Page specific html -->
                <h2>${team.name}</h2>
                <%if(!member.equals(team.getTeamLeader())) {%>
                <form role="form" id="drop-team-form" action="<c:out value="/team/${team.id}/removePlayer"/>" method="POST">
                    <button id="drop-team-submit" type="submit" class="btn btn-default">Drop Team</button>
                </form>
                <%} %>
                <div id="accordion" class="panel-group">
                    <div id="my-players-panel"
                        class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-target="#my-players-content" href="#my-players-content"> Players </a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse"
                            id="my-players-content">
                            <div class="panel-body">
                                <table class="table table-bordered">
                                    <tbody>
                                        <c:forEach items="${team.players}" var="player">
                                            <tr>
                                                <td><c:out value="${player.name}" /></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div id="my-invited-panel"
                        class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-target="#my-invited-content" href="#my-invited-content"> Invited Players </a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse"
                            id="my-invited-content">
                            <div class="panel-body">
                                <table class="table table-bordered">
                                    <tbody>
                                        <c:forEach items="${team.invitedPlayers}" var="player">
                                            <tr>
                                                <td><c:out value="${player.name}" /></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div id="my-games-panel" class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-target="#my-games-content" href="#my-games-content"> Games</a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse"
                            id="my-games-content">
                            <div class="panel-body">
                                <table class="table table-bordered">
                                    <tbody>
                                        <c:forEach items="${team.games}"
                                            var="game">
                                            <tr>
                                                <td><a href="<c:out value="/game/${game.id}/view"/>"><c:out value="Game ${game.id}" /></a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    
                    <% if (member.getId() == team.getTeamLeader().getId()) { %>
                    <div id="goto-team-panel" class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-target="#update-team-content" href="#update-team-content">Update Team</a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse"
                            id="update-team-content">
                            <div class="panel-body">
                                <form role="form" id="update-team-form">
                                    <div class="form-group">
                                        <label for="name">New Team Name:</label>
                                        <input type="text" class="form-control" id="teamName" name="teamName">
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Username Player to invite:</label>
                                        <input type="text" class="form-control" id="addPlayer" name="addPlayer">
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Username Player to Remove:</label>
                                        <input type="text" class="form-control" id="removePlayer" name="removePlayer">
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Username of New Captain:</label>
                                        <input type="text" class="form-control" id="newCaptain" name=newCaptain>
                                    </div>
                                    <div class="form-group">
                                        <label for="name">New Team Password:</label>
                                        <input type="password" class="form-control" id="newPassword" name=newPassword>
                                    </div>
                                    <button id="update-game-submit" type="submit" class="btn btn-default">Submit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <% } %>
                </div>
            </div>
            <jsp:include page="sideBar.jsp" />
        </div>
    </div>
</body>
<footer>
    <!-- jQuery library -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

    <!-- Bootstrap JavaScript plug-ins -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

    <!-- Page specific JS -->
    <script src="../../resources/js/team.js"></script>
    <script type="text/javascript">
        var teamId = '<c:out value="${team.id}"/>';
    </script>
    <script src="../../resources/js/header.js"></script>
</footer>
</html>
