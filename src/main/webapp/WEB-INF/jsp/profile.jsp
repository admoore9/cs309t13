<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
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
        <div class="container"><jsp:include page="header.jsp" flush="true"/></div>
        <div class="container">
            <p>Welome, ${sessionScope.member.name}</p>
        </div>
    </body>
</html>
