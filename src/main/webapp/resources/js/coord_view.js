$(document).ready(function() {
    $(".newUserTypeBtn").click(function() {
        $("#newUserType").val($(this).val());
    });
    
    $('#promote-demote-form').submit ( function (e) {
        e.preventDefault();
        var url = "/profile/promote";
        $.post(url, {
            username: $('#username').val(),
            newUserType: $('#newUserType').val()
        }, function() {
            location.reload(true);
        });
    });
});