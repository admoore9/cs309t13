$(document).ready(function() {
    $('#promote-demote-form').submit ( function (e) {
        e.preventDefault();
        var url = "/profile/promote";
        $.post(url, {
            username: $('#username').val(),
            newUserType: $('#userType').val()
        });
    });
});