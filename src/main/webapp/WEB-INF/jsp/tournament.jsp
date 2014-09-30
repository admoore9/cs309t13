<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <h2>Tournament</h2>
        <table>
            <c:forEach items="${tournaments}" var="tournament">
                <tr>
                    <td>Tournament ID: <c:out value="${tournament.id}"/></td>
                    <td>Tournament Name: <c:out value="${tournament.name}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
    
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <!-- <script type="text/javascript" src="/resources/js/tournament.js"></script> -->
    <script type="text/javascript" src="<c:url value="/resources/js/tournament.js" />"></script>
</html>
