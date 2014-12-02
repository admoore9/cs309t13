$(document).ready(function() {
    $('#update-team-form').on('submit', function(event) {
        event.preventDefault();
        var url = "/team/" + teamId + "/update";
        var data = $('#update-team-form').serialize();
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function(data) {
                window.location.href = "/team/" + teamId + "/view";
            }
        });
    });
});
