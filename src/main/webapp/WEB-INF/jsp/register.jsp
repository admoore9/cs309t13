<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- CSS -->
        <link rel="stylesheet" href="../../resources/js/bootstrap/css/bootstrap.min.css">
        
        <!-- jQuery library -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        
        <!-- Bootstrap JavaScript plug-ins -->
        <script src="../../resources/js/bootstrap/js/bootstrap.min.js"></script>
        <script src="../../resources/js/register.js"></script>
        
        <!-- Bootstrap validator -->
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>
    </head>
    <body>
        <div class="container"><jsp:include page="header.jsp" flush="true"/></div>
        <div class="container" id = "registerContainer">
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
</html>