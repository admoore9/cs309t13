/**
 * 
 */
$(document).ready(function() {
    $('#create-team-form').on('submit', function(event) {
        event.preventDefault();
        $.post('/team/create', {
            name: $('#create-team-name-input').val(),
            invitedPlayerId: $('#create-team-invitedPlayerId-input').val(),
        });
    });

});
