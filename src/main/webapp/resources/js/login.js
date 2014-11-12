$( document ).ready(function() {
	$('#loginForm').bootstrapValidator({
		message: 'This value is not valid',
		fields: {
			username: {
				validators: {
					notEmpty: {
						message: 'A username is required'
					}
				}
			},
			password: {
				validators: {
					notEmpty: {
						message: 'A password is required'
					}
				}
			}
		}
	})
	.on('success.form.bv', function(e) {
		// Stop form from submitting normally
		e.preventDefault();
		var url = "/login/submit";
		$.ajax({
			type: "POST",
			url: url,
			data: $('#loginForm').serialize(),
			success: function(result) {
				window.location.href = "/profile";
			}
		});
	});
});
