<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<%@ page import="javax.persistence.EnumType"%>
<%@ page import="edu.iastate.models.Member"%>
<% Member member = (Member) session.getAttribute("member"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Create Team</title>

<!-- CSS -->
<link rel="stylesheet"
	href="../../resources/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" />

<!-- Page specific CSS -->
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-9 col-md-8 col-sm-12 col-xs-12">
				<div class="container" style="margin-left: 10px;">
					<h2>Create Team</h2>
					<div id="panels" class="panel-group">
						<div id="create-team-panel" class="panel panel-default">
							<div class="panel-heading" data-toggle="collapse"
								data-target="#create-team-content">
								<h3 class="panel-title">Create Team</h3>
							</div>
							<div class="panel panel-collapse collapse"
								id="create-team-content">
								<div class="panel-body">
									<form role="form" id="create-team-form">
										<div class="form-group">
											<label for="name">Team name:</label> <input type="text"
												class="form-control" id="create-team-name-input">
										</div>
										<div class="form-group">
											<label for="invitedPlayerId">Invited Player ID:</label> <input
												type="text" class="form-control"
												id="create-team-invitedPlayerId-input">
										</div>
										<button id="create-team-submit" type="submit"
											class="btn btn-default">Submit</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="sideBar.jsp" />
		</div>
	</div>
</body>
<footer>
	<!-- jQuery library -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

	<!-- Bootstrap JavaScript plug-ins -->
	<script src="../../resources/js/bootstrap/js/bootstrap.min.js"></script>

	<!-- Bootstrap validator -->
	<script type="text/javascript"
		src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>

	<!-- Page specific JS -->
	<script src="../../resources/js/createTeam.js"></script>
</footer>
</html>



