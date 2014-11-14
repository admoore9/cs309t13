<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="../../resources/js/bootstrap/css/bootstrap.min.css">
        <title>Create Team</title>
    </head>
    <body>
        <div class="container" style="margin-left: 10px;">
            <h2>Create Team</h2>

            <div id="panels" class="panel-group">

                <div id="create-team-panel" class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse" data-target="#create-team-content">
                        <h3 class="panel-title">Create Team</h3>
                    </div>
                    <div class="panel panel-collapse collapse" id="create-team-content">
                        <div class="panel-body">
                            <form role="form" id="create-team-form">
                                <div class="form-group">
                                    <label for="name">Team name:</label>
                                    <input type="text" class="form-control" id="create-team-name-input">
                                </div>
                                <div class="form-group">
                                    <label for="tournamentId">Tournament ID:</label>
                                    <input type="text" class="form-control" id="create-team-tournamentId-input">
                                </div> 
                                <div class="form-group">
                                    <label for="invitedPlayerId">Invited Player ID:</label>
                                    <input type="text" class="form-control" id="create-team-invitedPlayerId-input">
                                </div>
                                <button id="create-team-submit" type="submit" class="btn btn-default">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="../../resources/js/bootstrap/js/bootstrap.min.js"></script>
        <script src="../../resources/js/createTeam.js"></script>
    </body>
</html>
