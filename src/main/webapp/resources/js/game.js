$(document).ready(function() {
    $('#update-game-form').on('submit', function(event) {
        event.preventDefault();
        $.post('/game/' + gameId + '/update', {
            location: $('#update-game-location-input').val(),
            addOfficial: $('#update-official-add-input').val(),
            removeOfficial: $('#update-official-remove-input').val(),
            success: setTimeout(function(result) {
                window.location.href = "/game/" + gameId + "/view";
            }, 500),
        });
    });

    $('.date').datetimepicker({
        format: 'YYYY-MM-DD HH:mm'
    });
});
