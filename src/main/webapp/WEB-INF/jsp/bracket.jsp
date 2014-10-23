<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bracket.css'/>">
    </head>
    <body>
        <h1>The Bracket page</h1>
        <div id="bracket"></div>
    </body>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/bracket.js' />"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var bracket = new Bracket();
            bracket.appendBracket($('#bracket'));
        });
    </script>
</html>
