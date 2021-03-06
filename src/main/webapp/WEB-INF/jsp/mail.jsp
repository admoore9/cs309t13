<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="edu.iastate.models.Member"%>
<%
    Member member = (Member) session.getAttribute("member");
%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Mail</title>
    
    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" />
    
    <!-- Page specific CSS -->
    <link rel="stylesheet" type="text/css" href="../../resources/css/mail.css">
</head>

<body>
    <jsp:include page="header.jsp" />
    <div class="container-fluid">
        <div class="row" style="margin-bottom: 4px;">
            <div id="mail-sidebar" class="col-lg-9 col-md-8 col-sm-12 col-xs-12">
                <!-- Button trigger modal -->
                <div class="btn-group">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#composeModal">Compose</button>
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded ="false">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a id="inbox" href="/mail/?inbox">
                                    Inbox
                                    <%
                                    int unviewedMessagesNum = member.getMail().getUnviewedMessages().size();
                                    if (unviewedMessagesNum > 0)
                                        out.print("(" + unviewedMessagesNum + ")");
                                %>
                        </a></li>
                        <li><a id="sentmail" href="/mail/?sentmail">Sent Mail</a></li>
                        <li><a id="drafts" href="/mail/?drafts">
                                    Drafts
                                    <%
                                    int draftsNum = member.getMail().getDrafts().size();
                                    if (draftsNum > 0)
                                        out.print("(" + draftsNum + ")");
                                %>
                        </a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row">
            <div id="mailBox" class="mainbox col-lg-9 col-md-8 col-sm-12 col-xs-12">
                <div class="panel panel-primary">
                    <div class="panel-heading inbox-heading">
                        <h3 class="panel-title">Mail</h3>
                        <span id="delete-message" class="glyphicon glyphicon-trash selection-icon"></span>
                        <span id="mark-unread" class="glyphicon glyphicon-envelope selection-icon"></span>
                    </div>

                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        <c:forEach items="${messages}" var="message">
                            <c:if test='${!message.isDeleted() }'>
                                <div class="panel panel-default">
                                    <div ${!message.isViewed() ? 'class="panel-heading unviewed"' : 'class="panel-heading"'} role="tab"
                                        id="heading-${message.getMessageId()}" data-toggle="collapse" data-parent="#accordion"
                                        data-target="#collapse-${message.getMessageId()}" aria-expanded="true" aria-controls="collapse-${message.getMessageId()}"
                                        data-message-id="${message.getMessageId() }">
                                        <%if (!"sentmail".equals(request.getQueryString())) { %>
                                            <input type="checkbox" id="message-${message.getMessageId()}-checkbox" class="selection-checkbox" value="${message.getMessageId()}">
                                        <%}%>
                                        <c:if test="${message.isDraft() }">
                                            <button id="edit-draft" class="glyphicon glyphicon-pencil" data-draft-id="${message.getMessageId() }"
                                                data-draft-recipient="${message.getRecipient().getUsername() }" data-draft-subject="${message.getSubject() }"
                                                data-draft-body="${message.getBody() }"></button>
                                        </c:if>
                                        <%if ("sentmail".equals(request.getQueryString()) || "drafts".equals(request.getQueryString())) { %>
                                            <span class=message-sender>To: ${message.getRecipient().getName() }</span>
                                        <%}
                                        else {%>
                                        <span class=message-sender>${message.getSender().getName() }</span>
                                        <%} %>
                                        <span> ${message.getSubject()} </span>
                                        <span class="message-time">${message.getTime()}</span>
                                    </div>
                                    <div id="collapse-${message.getMessageId()}" class="panel-collapse collapse" role="tabpanel"
                                        aria-labelledby="heading-${message.getMessageId()}">
                                        <div class="panel-body">${message.getBody()}</div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                    <!-- accordion panel group -->


                    <!-- New Message Modal -->
                    <div class="modal fade" id="composeModal" tabindex="-1" role="dialog" aria-labelledby="composeModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">
                                        <span aria-hidden="true">&times;</span>
                                        <span class="sr-only">Close</span>
                                    </button>
                                    <h4 class="modal-title" id="composeModalLabel">New Message</h4>
                                </div>
                                <form role="form" id="message-form" data-draft-id="">
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <input id="new-message-recipient" type="text" class="message-input form-control input-md" placeholder="To" name="recipient">
                                        </div>
                                        <div class="form-group">
                                            <input id="new-message-subject" type="text" class="form-control input-md message-input" placeholder="Subject" name="subject">
                                        </div>
                                        <div class="form-group">
                                            <textarea id="new-message-body" class="form-control message-input" name="body"></textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button id="save-draft" type="submit" class="btn btn-default">Save Draft</button>
                                        <button id="send-message" type="submit" class="btn btn-primary">Send</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- panel -->
            </div>
            <jsp:include page="sideBar.jsp"/>
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
    <script src="../../resources/js/mail.js"></script>
</footer>

</html>