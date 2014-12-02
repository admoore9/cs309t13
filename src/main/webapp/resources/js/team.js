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
                document.open();
                document.write(data);
                document.close();
                window.location.href = "/team/" + teamId + "/view";
            }
        });
    });
});
