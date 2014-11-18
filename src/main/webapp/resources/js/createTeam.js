$(document).ready(function() {
    $('#create-team-form').bootstrapValidator({
		message: 'This value is not valid',
		fields: {
			name: {
				validators: {
					notEmpty: {
						message: 'Your Team Name is required'
					}
				}
			},
		}
	}).on('submit', function(event) {
        event.preventDefault();
        $.post('/team/create', {
            name: $('#create-team-name-input').val(),
            invitedPlayerId: $('#create-team-invitedPlayerId-input').val(),
        });
    });

});
