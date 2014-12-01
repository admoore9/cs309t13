$(document).ready(function() {
    $('#update-game-form').on('submit', function(event) {
        event.preventDefault();
        $.post('/game/' + gameId + '/update', {
            location: $('#update-game-location-input').val(),
            addOfficial: $('#update-official-add-input').val(),
            removeOfficial: $('#update-official-remove-input').val(),
        });
    });

});
