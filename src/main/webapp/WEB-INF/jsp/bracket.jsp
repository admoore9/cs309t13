<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bracket.css"/>">
    </head>
    <body>
        <bracket>
            <round>
                <game>
                    <team>Team 1a</team>
                    <team class="winner">Team 1b</team>
                </game>
                <game>
                    <team>Team 1c</team>
                    <team>Team 1d</team>
                </game>
            </round>
            <round>
                <game>
                    <team class="winner">Team 2a</team>
                    <team>Team 2b</team>
                </game>
            </round>
        </bracket>
    </body>
</html>
