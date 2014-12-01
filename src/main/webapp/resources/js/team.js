$(document).ready(function() {
    $('#update-team-form').on('submit', function(event) {
        event.preventDefault();
        $.post('/team/' + teamId + '/update', {
            teamName: $('#update-team-name-input').val(),
            addPlayer: $('#update-player-add-input').val(),
            removePlayer: $('#update-player-remove-input').val(),
        });
    });
});
