<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.persistence.EnumType" %>
<%@ page import="edu.iastate.models.Member" %>
<% Member member = (Member) session.getAttribute("member"); %>

<div class="container">
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">Intramurals</a>
            </div>
            <div class="navbar-collapse collapse">
                <% if (member == null) { %>
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
                <% } else {
                    String userType = member.getUserType().toString(); %>
                    <div class="navbar-right">
                        <ul class="nav navbar-nav navbar-right">

                            <% if (!userType.equals("PLAYER")) { %>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Context <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
    
                                        <% if (userType.equals("ADMIN")) { %>
                                          <li>
                                              <a href="#">Administrator</a>
                                          </li>
                                        <% } %>
    
                                        <% if (userType.equals("COORDINATOR") || userType.equals("ADMIN")) { %>
                                          <li>
                                              <a href="#">Coordinator</a>
                                          </li>
                                        <% } %>
    
                                        <li>
                                            <a href="#">Official</a>
                                        </li>
                                        <li>
                                            <a href="#">Player</a>
                                        </li>
                                    </ul>
                                </li>
                            <% } %>

                            <li><a href="/profile">Welcome, ${sessionScope.member.name}!</a></li>
                            <li><a href="/logout">Logout</a></li>
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