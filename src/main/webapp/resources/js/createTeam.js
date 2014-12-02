$( document ).ready(function() {
	console.log(tournamentId);
	$('#createTeamForm').bootstrapValidator({
		message: 'This value is not valid',
		fields: {
			teamName: {
				validators: {
					notEmpty: {
						message: 'Your team name is required'
					},
					stringLength: {
						max: 45,
						message: 'Your team name must be less than 46 characters long'
					},
					remote: {
						message: 'The team name is not available',
						url: '/team/' + tournamentId + '/available'
					}
				}
			},
		}
	})
});