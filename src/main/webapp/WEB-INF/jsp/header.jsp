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
        <script src="../../resources/js/login.js"></script>
        
        <!-- Bootstrap validator -->
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>
    </head>
    <body>
      <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Intramurals</a>
          </div>
          <div class="navbar-collapse collapse">
            <% if (session.getAttribute("member") == null) { %>
                <form class="navbar-form navbar-right" id="loginForm">
                    <div class="form-group">
                        <input id="username" name="username" type="text" placeholder="Username" class="form-control input-sm">
                    </div>
                    <div class="form-group">
                        <input id="password" name="password" type="password" placeholder="Password" class="form-control input-sm">
                    </div>
                    <div class="btn-group">
                        <input type="submit" name="submitLogin" class="btn btn-success" value="Login"/>
                    </div>
                    <a href="/register">Get signed up!</a>
                </form>
            <% } else { %>
                <div class="navbar-right">
                    <a type="button" class="btn btn-danger navbar-btn" href="/logout">Logout </a>
                </div>
            <% } %>
          </div>
        </div>
      </div>
      <br>
      <br>
      <br>
    </body>
</html>