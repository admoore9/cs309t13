<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Home</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"/>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="jumbotron">
        <div class="container">
            <h1>Iowa State Intramurals</h1>
            <p>
                Recreation Services invites you to participate in one of the largest and best intramural sports programs
                in the nation. With an overboard philosophy of "something for everyone," the Intramural Program offers over
                40 sports to meet the diverse interests of the student, faculty and staff population.
            </p>
        </div>
    </div>
    <div>
        <div class="container-fluid" id="surveyContainer">
            <div id="surveyBox" class="mainbox col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Upcoming Tournaments</h3>
                    </div> <!-- panel heading -->
                    <div class="panel-body">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Sport</th>
                                    <th>Registration Open</th>
                                    <th>Registration Close</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${tournaments}" var="tournament">
                                    <tr>
                                        <td><c:out value="${tournament.name}"/></td>
                                        <td>TO DO</td>
                                        <td>TO DO</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
    <script src="../../resources/js/header.js"></script>
</footer>
</html>
