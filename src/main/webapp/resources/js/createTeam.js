/**
 * 
 */
$(document).ready(function() {
    $('#create-team-form').bootstrapValidator({
		message: 'This value is not valid',
		fields: {
			name: {
				validators: {
					notEmpty: {
						message: 'Team Name is required'
					}
				}
			},
			tournamentId: {
				validators: {
					notEmpty: {
						message: 'TournamentID is required'
					}
				}
			},
		}
	})
	.on('submit', function(event) {
        event.preventDefault();
        $.post('/team/create', {
            name: $('#create-team-name-input').val(),
            tournamentId: $('#create-team-tournamentId-input').val(),
            invitedPlayerId: $('#create-team-invitedPlayerId-input').val(),
        });
    });

});
