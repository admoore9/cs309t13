<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
</html>
