$( document ).ready(function() {
	$('#surveyForm').bootstrapValidator({
		message: 'This value is not valid',
		fields: {
			sex: {
				validators: {
					notEmpty: {
						message: 'Your sex is required'
					}
				}
			},
			height: {
				validators: {
					notEmpty: {
						message: 'Your height is required'
					}
				}
			},
			weight: {
				validators: {
					notEmpty: {
						message: 'Your weight is required'
					}
				}
			},
			compYears: {
				validators: {
					notEmpty: {
						message: 'This question is required'
					}
				}
			},
			intsPlayed: {
				validators: {
					notEmpty: {
						message: 'This question is required'
					}
				}
			},
			compLvl: {
				validators: {
					notEmpty: {
						message: 'This question is required'
					}
				}
			},
			isClubPlayer: {
				validators: {
					notEmpty: {
						message: 'This question is required'
					}
				}
			}
		}
	})
	.on('success.form.bv', function(e) {
		// Stop form from submitting normally
		e.preventDefault();
		var url = "/survey/submit";
		$.ajax({
			type: "POST",
			url: url,
			data: $('#surveyForm').serialize(),
			success: function(data) {
				$('#surveyContainer').fadeOut(500);
				$('#surveyAlert').delay(500).fadeIn(500);
			}
		});
	})
});

function cancelForm() {
	window.history.back()
}