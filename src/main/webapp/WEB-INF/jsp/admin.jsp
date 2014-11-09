<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="../../resources/js/bootstrap/css/bootstrap.min.css">
		<title>Tournament Manager</title>
	</head>
	<body>
		<div class="container" style="margin-left: 10px;">
			<h2>Admin View</h2>

			<div id="panels" class="panel-group">

		        <div id="create-tournament-panel" class="panel panel-default">
		        	<div class="panel-heading" data-toggle="collapse" data-target="#create-tournament-content">
		        		<h3 class="panel-title">Create Tournament</h3>
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
		        	<div class="panel-heading" data-toggle="collapse" data-target="#recent-tournament-content">
		        		<h3 class="panel-title">Recent Tournaments</h3>
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
		        	<div class="panel-heading" data-toggle="collapse" data-target="#update-tournament-content">
		        		<h3 class="panel-title">Go To Tournament</h3>
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

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="../../resources/js/bootstrap/js/bootstrap.min.js"></script>
        <script src="../../resources/js/admin_view.js"></script>
	</body>
</html>
