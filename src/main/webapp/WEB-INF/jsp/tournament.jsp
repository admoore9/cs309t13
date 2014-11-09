<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bracket.css"/>">
    </head>
    <body>
        <div id="bracket"/>
    </body>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/tournament.js" />"></script>
    <script type="text/javascript">
    	$(document).ready(function() {
    	    var bracket = new Bracket();
            bracket.appendBracket($('#bracket'));
    	});
    </script>
</html>
