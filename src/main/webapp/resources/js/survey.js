$( document ).ready(function() {
	$('#surveyForm').submit(function(event) {
		// Stop form from submitting normally
		event.preventDefault();
		var url = "/survey";
		$.ajax({
			type: "POST",
			url: url,
			data: $('#surveyForm').serialize(),
			success: function(data) {
				alert(data);
			}
		});
	});
});

function validateForm() {
	return true;
};