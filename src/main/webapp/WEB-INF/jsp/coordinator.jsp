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

    <title>Admin</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"/>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-9 col-md-8 col-sm-12 col-xs-12">
                <h2>Coordinator View</h2>

                <div id="accordion" class="panel-group">

                    <div id="create-tournament-panel" class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-target="#create-tournament-content" href="#create-tournament-content">
                                    Create Tournament:
                                </a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse" id="create-tournament-content">
                            <div class="panel-body">
                                <form role="form" id="create-tournament-form">
                                    <div class="form-group">
                                        <label for="name">Tournament name:</label>
                                        <input type="text" class="form-control" id="create-tournament-name-input">
                                    </div>
                                    <div class="form-group">
                                        <label for="minPlayersPerTeam">Minimum players per team:</label>
                                        <input type="text" class="form-control" id="create-tournament-min-players-input">
                                    </div>
                                    <div class="form-group">
                                        <label for="maxPlayersPerTeam">Maximum players per team:</label>
                                        <input type="text" class="form-control" id="create-tournament-max-players-input">
                                    </div>
                                    <div class="form-group">
                                        <label for="teamsPerGame">Teams Per Game:</label>
                                        <input type="text" class="form-control" id="create-teams-per-game-input">
                                    </div>
                                    <div class="form-group">
                                        <label for="officialsPerGame">Officials Per Game:</label>
                                        <input type="text" class="form-control" id="create-officials-per-game-input">
                                    </div>
                                    <button id="create-tournament-submit" type="submit" class="btn btn-default">Submit</button>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div id="recent-tournament-panel" class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-target="#recent-tournament-content" href="#recent-tournament-content">
                                    Recent Tournaments:
                                </a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse" id="recent-tournament-content">
                            <div class="panel-body">
                                <table id="recent-tournaments">
                                    <c:forEach items="${tournaments}" var="tournament">
                                        <tr>
                                            <td>Tournament ID: <c:out value="${tournament.id}"/></td>
                                            <td>Tournament Name: <c:out value="${tournament.name}"/></td>
                                       </tr>
                                    </c:forEach>
                                 </table>
                            </div>
                        </div>
                    </div>

                    <div id="goto-tournament-panel" class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-target="#update-tournament-content" href="#update-tournament-content">
                                    Update Tournament:
                                </a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse" id="update-tournament-content">
                            <div class="panel-body">
                                <form class="form-inline" role="form" id="goto-tournament-form">
                                    <div class="form-group">
                                        <label for="tournamentId">Tournament Id:</label>
                                        <input type="text" class="form-control" id="update-tournament-id-input">
                                    </div>
                                    <button id="goto-tournament-submit" type="submit" class="btn btn-default">Go To Tournament</button>
                                </form>
                                <form role="form" id="update-tournament-form">
                                    <div class="form-group">
                                        <label for="name">Tournament name:</label>
                                        <input type="text" class="form-control" id="update-tournament-name-input">
                                    </div>
                                    <div class="form-group">
                                        <label for="minPlayersPerTeam">Minimum players per team:</label>
                                        <input type="text" class="form-control" id="update-tournament-min-players-input">
                                    </div>
                                    <div class="form-group">
                                        <label for="maxPlayersPerTeam">Maximum players per team:</label>
                                        <input type="text" class="form-control" id="update-tournament-max-players-input">
                                    </div>
                                    <div class="form-group">
                                        <label for="teamsPerGame">Teams Per Game:</label>
                                        <input type="text" class="form-control" id="update-teams-per-game-input">
                                    </div>
                                    <div class="form-group">
                                        <label for="officialsPerGame">Officials Per Game:</label>
                                        <input type="text" class="form-control" id="update-officials-per-game-input">
                                    </div>
                                    <button id="update-tournament-submit" type="submit" class="btn btn-default">Update Tournament</button>
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
    <script src="../../resources/js/coord_view.js"></script>
</footer>
</html>
