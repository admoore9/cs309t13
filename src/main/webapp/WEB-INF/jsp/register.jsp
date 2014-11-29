<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page import="javax.persistence.EnumType" %>
<%@ page import="edu.iastate.models.Member" %>
<% Member member = (Member) session.getAttribute("member"); %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Register</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"/>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="container-fluid" id = "registerContainer">
        <div id="register" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-msm-offset-2 col-xs-8 col-xs-offset-2">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Player Registration</h3>
                </div> <!-- panel heading -->
                <div class="panel-body">
                    <form role="form" id="registerForm">
                        <div class="panel-body">
                            <div class="form-group">
                                <label for="name">Name:</label>
                                <input id="name" name="name" type="text" class="form-control input-md">
                            </div>

                            <div class="form-group">
                                <label for="username">Username:</label>
                                <input id="username" name="username" type="text" class="form-control input-md">
                            </div>

                            <div class="form-group">
                                <label for="password">Password:</label>
                                <input id="password" name="password" type="password" class="form-control input-md">
                            </div>

                            <div class="btn-group">
                                <input type="submit" name="submitRegister" class="btn btn-primary" value="Submit!"/>
                            </div>
                        </div>
                    </form>
                </div> <!-- panel body -->
            </div> <!-- panel -->
        </div> <!-- register box -->
    </div> <!-- container -->
</body>
<footer>
    <!-- jQuery library -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

    <!-- Bootstrap JavaScript plug-ins -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

    <!-- Bootstrap validator -->
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>

    <!-- Page specific JS -->
    <script src="../../resources/js/register.js"></script>
</footer>
</html>