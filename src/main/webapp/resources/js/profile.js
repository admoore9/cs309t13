function acceptTeamForm(teamId) {
    var url = "/team/" + teamId + "/addPlayer";
    $.post(url);
}
function rejectTeamForm(teamId) {
    var url = "/team/" + teamId + "/rejectInvite";
    $.ajax({
        type: "POST",
        url: url
    });
}
$( document ).ready(function() {
    $('#edit-profile-form').bootstrapValidator({
        message: 'This value is not valid',
        fields: {
            name: {
                validators: {
                    stringLength: {
                        max: 45,
                        message: 'Your name must be less than 46 characters long'
                    }
                }
            },
            height: {
                validators: {
                    integer: {
                        message: 'Your height must be numeric'
                    },
                    between: {
                        min: 0,
                        max: 100,
                        message: 'Your height must be between 0 and 100'
                    }
                }
            },
            weight: {
                validators: {
                    integer: {
                        message: 'Your weight must be numeric'
                    },
                    between: {
                        min: 0,
                        max: 1000,
                        message: 'Your weight must be between 0 and 1000'
                    }
                }
            }
        }
    })
});