$( document ).ready(function() {
	$('#registerForm').bootstrapValidator({
		message: 'This value is not valid',
		fields: {
			name: {
				validators: {
					notEmpty: {
						message: 'Your name is required'
					},
					stringLength: {
						max: 45,
						message: 'Your name must be less than 46 characters long'
					}
				}
			},
			username: {
				validators: {
					notEmpty: {
						message: 'A username is required'
					},
					stringLength: {
						max: 45,
						message: 'Your username must be less than 46 characters long'
					},
					remote: {
						message: 'The username is not available',
						url: '/register/available'
					}
				}
			},
			password: {
				validators: {
					notEmpty: {
						message: 'A password is required'
					},
					stringLength: {
						max: 45,
						message: 'Your password must be less than 46 characters long'
					}
				}
			}
		}
	})
	.on('success.form.bv', function(e) {
		// Stop form from submitting normally
		e.preventDefault();
		var url = "/register/submit";
		$.ajax({
			type: "POST",
			url: url,
			data: $('#registerForm').serialize(),
			success: function(result) {
				window.location.href = "/login";
			}
		});
	});
});