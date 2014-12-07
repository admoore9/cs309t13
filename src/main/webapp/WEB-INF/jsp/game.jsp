<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page import="javax.persistence.EnumType" %>
<%@ page import="edu.iastate.models.Member" %>
<%@ page import="edu.iastate.utils.MemberUtils" %>
<% Member member = (Member) session.getAttribute("member"); %>

<!DOCTYPE html>
<c:out value="${game.id}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Game</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.1.3/css/bootstrap-datetimepicker.min.css"/>

    <!-- Page specific CSS -->
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-9 col-md-8 col-sm-12 col-xs-12">
                <h2>Tournament ${game.tournament.name}: Game ${game.id}</h2>
                <div id="teams-game-panel" class="panel panel-default">
                   <div class="panel-heading">
                       <h3 class="panel-title">
                           <a data-toggle="collapse" data-target="#my-${game.id}-teams" href="#my-${game.id}-teams">
                               Teams
                           </a>
                       </h3>
                   </div>
                   <div class="panel panel-collapse collapse" id="my-${game.id}-teams">
                       <div class="panel-body">
                           <table class="table table-bordered">
                               <tbody>
                                   <c:forEach items="${game.teams}" var="team">
                                       <tr>
                                           <td><a href="<c:out value="/team/${team.id}/view"/>"><c:out value="${team.name}"/></a></td>
                                       </tr>
                                   </c:forEach>
                               </tbody>
                           </table>
                       </div>
                   </div>
               </div>
               <div id="game-info-panel" class="panel panel-default">
                   <div class="panel-heading">
                       <h3 class="panel-title">
                           <a data-toggle="collapse" data-target="#my-${game.id}-info" href="#my-${game.id}-info">
                               Game Information
                           </a>
                       </h3>
                   </div>
                   <div class="panel panel-collapse collapse" id="my-${game.id}-info">
                       <div class="panel-body">
                           <table class="table table-bordered">
                               <tbody>
                                   <tr>
                                        <td>Game Location</td>
                                        <td>${game.gameLocation}</td>
                                   </tr>
                                   <tr>
                                        <td>Game Time</td>
                                        <td>${game.gameTime}</td>
                                   </tr>
                                   <tr>
                                        <td>Game Officials</td>
                                        <td>
                                        <c:forEach items="${game.officials}" var="official">
	                                           ${official.name},
	                                    </c:forEach>
	                                   </td>
	                              </tr>
                               </tbody>
                           </table>
                       </div>
                   </div>
               </div>
               <% if (MemberUtils.atLeastCoordinator(member)) { %>
               <div id="goto-game-panel" class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <a data-toggle="collapse" data-target="#update-game-content" href="#update-game-content">
                                Update Game:
                            </a>
                        </h3>
                    </div>
                    <div class="panel panel-collapse collapse" id="update-game-content">
                        <div class="panel-body">
                            <form role="form" id="update-game-form">
                                <div class="form-group">
                                    <label for="name">Game Location:</label>
                                    <input value="${game.getGameLocation()}" type="text" class="form-control" id="update-game-location-input">
                                </div>
                                <div class="form-group input-group">
                                    <label for="gameTime">Game Time:</label>
                                    <input type='text' class="form-control date" id="update-game-time-input">
                                </div>
                                <div class="form-group">
                                    <label for="name">Username Official to Add:</label>
                                    <input type="text" class="form-control" id="update-official-add-input">
                                </div>
                                <div class="form-group">
                                    <label for="name">Username Official to Remove:</label>
                                    <input type="text" class="form-control" id="update-official-remove-input">
                                </div>
                                <button id="update-game-submit" type="submit" class="btn btn-default">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
                <% } %>
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

    <!-- Bootstrap validator (if needed) -->
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.1.3/js/bootstrap-datetimepicker.min.js"></script>

    <!-- Page specific JS -->
    <script src="../../resources/js/game.js"></script>
    <script type="text/javascript">
        var gameId = '<c:out value="${game.id}"/>';
    </script>
</footer>
</html>
