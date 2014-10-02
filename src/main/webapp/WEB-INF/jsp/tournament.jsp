<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <h2>Tournament</h2>
        <h4>Recent Tournaments</h4>
        <table id="recent-tournaments">
            <c:forEach items="${tournaments}" var="tournament">
                <tr>
                    <td>Tournament ID: <c:out value="${tournament.id}"/></td>
                    <td>Tournament Name: <c:out value="${tournament.name}"/></td>
                </tr>
            </c:forEach>
        </table>
        <h4>Get tournament by id</h4>
        <input type="number" id="tournament-by-id-input"/>
        <div id="tournament-by-id-data"></div>
    </body>
    
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/tournament.js" />"></script>
</html>
