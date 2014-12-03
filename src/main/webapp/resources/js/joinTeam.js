$( document ).ready(function() {
    $('#join-team' + teamId + '-form').on('submit', function(event) {
        event.preventDefault();
        console.log(teamId);
        var url = "/team/" + teamId + "/joinTeam";
        var data = $('#join-team' + teamId + '-form').serialize();
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