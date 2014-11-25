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

    <!-- Page specific CSS -->
    
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-9 col-md-8 col-sm-12 col-xs-12">

                <!-- Page specific html -->
                <div class="container-fluid" id = "createTeamContainer">
                    <div id="createTeamBox" class="mainbox col-md-8 col-md-offset-2 col-sm-10 col-msm-offset-1 col-xs-10 col-xs-offset-1">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Create Team</h3>
                            </div> <!-- panel heading -->
                            <div class="panel-body">
                                <form role="form" id="createTeamForm" action="<c:out value="/team/${tournamentId}/create/submit"/>" method="POST">
                                    <div class="panel-body">
                                    
                                        <div class="form-group">
                                            <label for="teamName">Team name:</label>
                                            <input id="teamName" name="teamName" type="text" class="form-control input-md">
                                        </div>
            
                                        <div class="form-group">
                                            <label for="invitedPlayerUsername">Invited Player Username:</label>
                                            <input id="invitedPlayerUsername" name="invitedPlayerUsername" type="text" class="form-control input-md">
                                        </div>
            
                                        <div class="btn-group">
                                            <input type="submit" name="submitCreateTeam" class="btn btn-primary" value="Submit!"/>
                                        </div>
                                    </div>
                                </form>
                            </div> <!-- panel body -->
                        </div> <!-- panel -->
                    </div> <!-- register box -->
                </div> <!-- container -->
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
    <script src="../../resources/js/createTeam.js"></script>
</footer>
</html>
