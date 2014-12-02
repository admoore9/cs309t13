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
        $.post(url, table.$('input, select').serialize(), function(data) {
            document.open();
            document.write(data);
            document.close();
        });
    });

    resetSelectAll();

    $('#selectall').click(function() {
        $('.checkbox').prop('checked', isChecked('selectall'));
    });
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