<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Notification</title>
<!-- CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" />

<!-- Page specific CSS -->
<link rel="stylesheet" type="text/css"
	href="/resources/css/mail.css">
</head>

<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<div id="sidebar" class="col-lg-1 col-md-1 col-sm-1">
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#composeModal">
			  Compose
			</button>
			<a id="inbox" href="/mail/?inbox">Inbox</a>
			<a id="sentmail" href="/mail/?sentmail">Sent Mail</a>
			<a id="drafts" href="/mail/?drafts">Drafts</a>
			<a id="deleted" href="/mail/?deleted">Deleted</a>
		</div>
		<div id="mailBox"
			class="mainbox col-lg-11 col-md-11 col-sm-11">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Mail</h3>
				</div>

				<div class="panel-group" id="accordion" role="tablist"
					aria-multiselectable="true">
					<c:forEach items="${messages}" var="message">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab"
								id="heading-${message.getMessageId()}">
								<h4 ${!message.isViewed() ? 'class="panel-title bold"' : 'class="panel-title"'}>
									<span class=message-sender>${message.getSender().getName() }</span>
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse-${message.getMessageId()}"
										aria-expanded="true"
										aria-controls="collapse-${message.getMessageId()}">
										${message.getTitle()} </a>
									<span class="message-time">${message.getTime()}</span>
								</h4>
							</div>
							<div id="collapse-${message.getMessageId()}"
								class="panel-collapse collapse" role="tabpanel"
								aria-labelledby="heading-${message.getMessageId()}">
								<div class="panel-body">${message.getBody()}</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<!-- accordion panel group -->


				<!-- New Message Modal -->
				<div class="modal fade" id="composeModal" tabindex="-1" role="dialog" aria-labelledby="composeModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				        <h4 class="modal-title" id="composeModalLabel">New Message</h4>
				      </div>
				      <div class="modal-body">
				        <form class="navbar-form navbar-left">
						  <div class="form-group">
						    <input type="text" class="form-control" placeholder="To">
						    <br>
						    <input type="text" class="form-control" placeholder="Subject">
						    <textarea class="new-message-body"></textarea>
						  </div>
						</form>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default">Save Draft</button>
				        <button type="submit" class="btn btn-primary" data-dismiss="modal">Send</button>
				      </div>
				    </div>
				  </div>
				</div>
				
			</div>
			<!-- panel -->
		</div>
		<!-- survey box -->
	</div>
</body>
<footer>
	<!-- jQuery library -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

	<!-- Bootstrap JavaScript plug-ins -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

	<!-- Page specific JS -->
	<script src="../../resources/js/mail.js"></script>
</footer>

</html>