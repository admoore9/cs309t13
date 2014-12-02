$(document).ready(function() {
    $('#update-team-form').bootstrapValidator({
		message: 'This value is not valid',
		fields: {
			teamName: {
				validators: {
					notEmpty: {
						message: 'A team name is required'
					},
					stringLength: {
						max: 45,
						message: 'Your team name must be less than 46 characters long'
					},
					remote: {
						message: 'The team name is not available',
						url: '/team/available'
					}
				}
			}
		}
	})
	.on('success.form.bv', function(event) {
        event.preventDefault();
        $.post('/team/' + teamId + '/update', {
            teamName: $('#update-team-name-input').val(),
            addPlayer: $('#update-player-add-input').val(),
            removePlayer: $('#update-player-remove-input').val(),
            success: setTimeout(function(result) {
				window.location.href = "/team/" + teamId + "/view";
			}, 500),
        });
    });
});
