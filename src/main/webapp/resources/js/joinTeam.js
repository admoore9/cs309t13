$( document ).ready(function() {
    $('#join-team-form').on('submit', function(event) {
        event.preventDefault();
        var url = "/team/" + teamId + "/addPlayer";
        $.ajax({
            type: "POST",
            url: url,
            success: function() {
                window.location.href = "/profile";
            }
        });
    });
});