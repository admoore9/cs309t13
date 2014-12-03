$( document ).ready(function() {
    $('#join-team-form').on('submit', function(event) {
        event.preventDefault();
        var url = "/team/" + teamId + "/joinTeam";
        var data = $('#join-team-form').serialize();
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function(data) {
                window.location.href = "/profile";
            }
        });
    });
});