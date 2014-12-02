$(document).ready(function() {

    $('#availabilityTable').DataTable({
        "paging" : false,
        "ordering" : false,
        "info" : false,
        "filter" : false
    });

    $('#update').click(function(e) {
        var table = $('#availabilityTable').DataTable();
        e.preventDefault();
        var url = "/availability/update";
        post(url, table.$('input, select').serialize());
    });

    resetSelectAll();

    $('#selectall').click(function() {
        $('.checkbox').prop('checked', isChecked('selectall'));
    });

    function post(path, params, method) {
        method = method || "post";
        var form = document.createElement("form");
        form.setAttribute("method", method);
        form.setAttribute("action", path);

        var keyvalues = params.split("&");
        for (var i = 0; i < keyvalues.length; i++) {
            var pair = keyvalues[i].split("=");
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", pair[0]);
            hiddenField.setAttribute("value", pair[1]);
            form.appendChild(hiddenField);
        }
        document.body.appendChild(form);
        form.submit();
    }
});

function isChecked(checkboxId) {
    var id = '#' + checkboxId;
    return $(id).is(":checked");
}
function resetSelectAll() {
    if ($(".checkbox").length == $(".checkbox:checked").length) {
        $("#selectall").attr("checked", "checked");
    } else {
        $("#selectall").removeAttr("checked");
    }

    if ($(".checkbox:checked").length > 0) {
        $('#edit').attr("disabled", false);
    } else {
        $('#edit').attr("disabled", true);
    }
}