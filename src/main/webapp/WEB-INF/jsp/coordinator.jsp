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

    <title>Coordinator</title>

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

                    <div id="my-teams-panel" class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-target="#my-teams-content" href="#my-teams-content">
                                    Manage My Teams
                                </a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse" id="my-teams-content">
                            <div class="panel-body">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Teams</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${teams}" var="team">
                                            <tr>
                                                <td><a href="<c:out value="/team/${team.id}/view"/>"><c:out value="${team.name}"/></a></td>
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
                                <a data-toggle="collapse" data-target="#my-games-content" href="#my-games-content">
                                    Manage My Games
                                </a>
                            </h3>
                        </div>
                        <div class="panel panel-collapse collapse" id="my-games-content">
                            <div class="panel-body">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Games</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${games}" var="game">
                                            <tr>
                                                <td><a href="<c:out value="/game/${game.id}/view"/>"><c:out value="${game.id}"/></a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
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
