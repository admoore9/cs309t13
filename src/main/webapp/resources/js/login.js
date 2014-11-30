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
});
