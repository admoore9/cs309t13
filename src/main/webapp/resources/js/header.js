$( document ).ready(function() {
    $('#success').fadeIn(500);
    $('#danger').fadeIn(500);
    $('#success').fadeOut(5000).slideUp(500);
    $('#danger').fadeOut(5000).slideUp(500);
    $('body').on('click', '#messages', function(e) {
        var url = "/mail/setMessagesAsViewed";
        $.ajax({
            type: "POST",
            url: url,
            success : function(result) {
                document.getElementById("messageBadge").innerHTML = 0;
            }
        });
    });
});