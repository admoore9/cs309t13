<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bracket.css"/>">
    </head>
    <body data-tournament-id="<c:out value='${tournamentId}'/>">
        <h2>Bracket</h2>
        <h3>Tournament Name: <span id="tournament-name"></span></h3>
        <div id="bracket"></div>
    </body>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bracket.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/tournament.js" />"></script>
    <script type="text/javascript">
    	$(document).ready(function() {
    	    var bracket = new Bracket($('body').data('tournament-id'));
            bracket.formAndAppendBracket($('#bracket'), $('#tournament-name'));
    	});
    </script>
</html>
