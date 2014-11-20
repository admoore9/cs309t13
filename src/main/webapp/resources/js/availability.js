$( document ).ready(function() {
	
	$('#availabilityTable').DataTable({
        "paging":   false,
        "ordering": false,
        "info":     false,
        "filter":	false
	});
	
    $('#update').click( function(e) {
        var table = $('#availabilityTable').DataTable();
		e.preventDefault();
		var url = "/availability/submit";
		$.ajax({
			type: "POST",
			url: url,
			data: table.$('input, select').serialize(),
			success: function(result) {
				window.location.href = "/availability";
			}
		});
        
        return false;
    } );

    resetSelectAll();

    $('#selectall').click(function () {
        $('.checkbox').prop('checked', isChecked('selectall'));
    });
});


function isChecked(checkboxId) {
    var id = '#' + checkboxId;
    return $(id).is(":checked");
}
function resetSelectAll() {
    // if all checkbox are selected, check the selectall checkbox
    // and viceversa
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