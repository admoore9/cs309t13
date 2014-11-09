<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bracket.css"/>">
    </head>
    <body data-player-id="<c:out value='${player.id}'/>">
        <h2><c:out value="${player.name}"/></h2>
        <div class="panel-group" id="team-leader-panels"></div>
        <div class="panel-group" id="team-participant-panels"></div>
    </body>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/player.js" />"></script>
</html>
