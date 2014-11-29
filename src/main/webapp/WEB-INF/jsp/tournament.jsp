<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Tournament</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"/>

    <!-- Page specific CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bracket.css"/>">
</head>
<body data-tournament-id="<c:out value='${tournament.id}'/>" data-tournament-formed="<c:out value='${tournament.isBracketFormed()}'/>">
    <jsp:include page="header.jsp"/>
    <h2>Bracket</h2>
    <h3>Tournament Name: <span id="tournament-name"></span></h3>

    <c:choose>
        <c:when test="${tournament.isBracketFormed()}">
            <div id="bracket"></div>
        </c:when>

        <c:otherwise>
            <br/>
            <div class="btn btn-primary" id="form-bracket">Form bracket</div>
        </c:otherwise>
    </c:choose>
</body>
<footer>
    <!-- jQuery library -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

    <!-- Bootstrap JavaScript plug-ins -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

    <!-- Bootstrap validator -->
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>

    <!-- Page specific JS -->
    <script src="../../resources/js/login.js"></script>
    <script src="../../resources/js/tournament.js"></script>
    <script type="text/javascript">
        if($('body').data('tournament-formed')) {
            var bracket = new Bracket($('body').data('tournament-id'));
            bracket.formAndAppendBracket($('#bracket'), $('#tournament-name'));
        } else {
            $('#form-bracket').on('click', function(event) {
                event.preventDefault();
                var tournamentId = $('body').data('tournament-id');
                $.post('/tournament/' + tournamentId + '/form');
            });
        }
    </script>
</footer>
</html>
