$( document ).ready(function() {
	$('#surveyForm').submit(function(event) {
		// Stop form from submitting normally
		event.preventDefault();
		var url = "/survey/submit";
		$.ajax({
			type: "POST",
			url: url,
			data: $('#surveyForm').serialize(),
			success: function(data) {
				$('#surveyForm').fadeOut(500);
				$('#surveyAlert').delay(500).fadeIn(500);
			}
		});
	});
});

function cancelForm() {
	window.history.back()
}