<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="javax.persistence.EnumType" %>
<%@ page import="edu.iastate.models.Member" %>
<%@ page import="edu.iastate.models.Message" %>
<%@ page import="edu.iastate.dao.MessageDao" %>
<% Member member = (Member) session.getAttribute("member"); %>

<head>
    <!-- Page specific CSS -->
    <link rel="stylesheet" type="text/css" href="/resources/css/header.css">
</head>

<div class="container-fluid">
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">Iowa State Intramurals</a>
            </div>
            <div class="navbar-collapse collapse">
                <% if (member == null) { %>
                <form class="navbar-form navbar-right" id="loginForm" action="/login" method="POST">
                    <div class="form-group">
                        <input id="username" name="username" type="text" placeholder="Username" class="form-control input-sm">
                    </div>
                    <div class="form-group">
                        <input id="password" name="password" type="password" placeholder="Password" class="form-control input-sm">
                    </div>
                    <div class="btn-group">
                        <input type="submit" name="submitLogin" class="btn btn-success" value="Login"/>
                    </div>
                    <a href="/register">Sign up!</a>
                </form>
                <% } else {
                Member.UserType userType = member.getUserType();%>
                <div class="navbar-right">
                    <ul class="nav navbar-nav navbar-right">

                        <li><a href="/context/home">Welcome, ${sessionScope.member.name}!</a></li>
                        <% if (userType != Member.UserType.PLAYER) { %>
                        <li>
                            <form id="context-form" action="/context" method="POST">
                                <input type="hidden" name="context" id="context">
                            </form>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="text-transform: capitalize">
                                <c:out value="${ fn:toLowerCase(member.context) }"/> <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <% if (userType == Member.UserType.ADMIN) { %>
                                    <li><a href="#" onclick="$('#context').val('3'); $('#context-form').submit()">Administrator</a></li>
                                <% } %>

                                <% if (userType == Member.UserType.COORDINATOR || userType == Member.UserType.ADMIN) { %>
                                    <li><a href="#" onclick="$('#context').val('2'); $('#context-form').submit()">Coordinator</a></li>
                                <% } %>

                                <li><a href="#" onclick="$('#context').val('1'); $('#context-form').submit()">Official</a></li>
                                <li><a href="#" onclick="$('#context').val('0'); $('#context-form').submit()">Player</a></li>
                            </ul>
                        </li>
                        <% } %>

                        <li id="messages" class="dropdown"><a href="#"
                            class="dropdown-toggle" data-toggle="dropdown">Message <span
                                id="messageBadge" class="badge">
                                    <%
                                        out.print(member.getMail().getUnviewedMessages().size());
                                    %>
                            </span></a>
                            <ul class="dropdown-menu">
                                <% for (Message message : member.getMail().getUnviewedMessages()) {
                                        out.print("<li class='message'>" + message.getSubject() + " " + "<span class='message-time'>" + message.getTime() + "</span>" + "</li>");
                                    } %>
                                <% if (!member.getMail().getUnviewedMessages().isEmpty()) { %>
                                    <li role="presentation" class="divider"></li>
                                <% } %>
                                <li class="inbox"><a href="/mail">Inbox</a></li>
                            </ul>
                        </li>
                        <li style="margin-right: 15px;"><a href="/logout">Logout</a></li>
                    </ul>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</div>
<br>
<br>
<br>
<c:if test="${!empty message}">
    <div id="success" class="alert alert-success alert-dismissible" role="alert" style="display: none;">
        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <strong>Success!</strong> ${message}
    </div>
</c:if>
<c:if test="${!empty errorMessage}">
    <div id="danger" class="alert alert-danger alert-dismissible" role="alert" style="display: none;">
        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <strong>Warning!</strong> ${errorMessage}
    </div>
</c:if>

<footer>
    <!-- jQuery library -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Page specific JS -->
    <script src="../../resources/js/header.js"></script>
</footer>
