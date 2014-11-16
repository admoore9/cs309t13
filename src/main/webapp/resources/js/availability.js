$( document ).ready(function() {
	
	$('#myTable').DataTable({
        "paging":   false,
        "ordering": false,
        "info":     false,
        "filter":	false
	});
	
    $('#submit').click( function(e) {
    	var table = $('#myTable').DataTable();
//        var data = table.$('input, select').serialize();
//        alert(
//            "The following data would have been submitted to the server: \n\n"+
//            data
//        );
        
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
});