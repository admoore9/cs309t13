<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bracket.css"/>">
    </head>
    <body data-player-id="<c:out value='${player.id}'/>">
        <h2><c:out value="${player.name}"/></h2>
        <c:forEach items="${teams}" var="team">
            <tr>
                <strong>Team: </strong><a href="/team/<c:out value='${team.id}'/>/view"><c:out value="${team.name}"/></a>
            </tr>
        </c:forEach>
    </body>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/player.js" />"></script>
</html>
