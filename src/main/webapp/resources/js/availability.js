$( document ).ready(function() {
	$('body').append("<h1>SALAM</h1>");
	$('#myTable').DataTable({
        "paging":   false,
        "ordering": false,
        "info":     false,
        "filter":	false
	});
});