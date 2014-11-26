$(document).ready(function() {
    $('body').on('click', '#messages', function(e) {
		var url = "/mail/setMessagesAsViewed";
		$.ajax({
			type: "POST",
			url: url
		});
    });
});