$( document ).ready(function() {
    $('#createTeamForm').bootstrapValidator({
        message: 'This value is not valid',
        fields: {
            teamName: {
                validators: {
                    notEmpty: {
                        message: 'Your Team Name is required'
                    }
                }
            },
            invitedPlayerId: {
                validators: {
                    notEmpty: {
                        message: 'An invited player id is required'
                    }
                }
            }
        }
    })
    .on('success.form.bv', function(event) {
        // Stop form from submitting normally
        event.preventDefault();
        var url = '/team/create';
        $.ajax({
            type: "POST",
            url: url,
            data: $('#createTeamForm').serialize(),
            success: function(data) {
                window.location.href = "/profile";
            }
        });
    })
});
