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

    <title>Game</title>

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
</footer>
</html>
