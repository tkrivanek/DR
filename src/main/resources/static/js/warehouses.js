//setting disabled on endDate field
$( document ).ready(function() {
	document.getElementById("endDate").disabled = true;
});

//datepicker for fromDate input
$("#fromDate").datepicker({
	format : "dd/mm/yyyy",
	language : "hr",
	todayHighlight : true,
	startDate : "today",
	autoclose: true
});

//datepicker for endDate input
$("#endDate").datepicker({
	format : "dd/mm/yyyy",
	language : "hr",
	todayHighlight : true,
	startDate : "today",
	autoclose: true
});

//setting disabled true or false on dependent fields
$('#fromDate').on('change', function() {
	if(document.getElementById("fromDate").value !== ''){
		document.getElementById("endDate").disabled = false;
	}else {
		document.getElementById("endDate").disabled = true;
		document.getElementById("endDate").value = "";
		document.getElementById("calculated").value = "";
	}
});

//calling fuction on change of endDate input
$('#endDate').on('change', function() {
	calcDiff();
});

//calculating days between dates and total price of rent
function calcDiff() {
	var date1 = $('#fromDate').datepicker('getDate');
	var date2 = $('#endDate').datepicker('getDate');
	var diff = 0;
	var price = $('#dailyPrice').val();
	var totPrice = 0;
	if (date1 && date2) {
		diff = Math.floor((date2.getTime() - date1.getTime()) / 86400000);
		if(price){
			totPrice = price * diff;
		}
	}
	$('#numberOfDays').val(diff);
	$('#price').val(totPrice + ' HRK');
}

//setting warehouse attributes for canceling rent 
$('#confirmCancelRentModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget) // Button that triggered the modal
	var warehouseId = button.data('warehouse-id') // Extract info from data-* attributes
	var userId = button.data('user-id') // Extract info from data-* attributes
	$('#warehouseId').val(warehouseId)
	$('#userId').val(userId)

})

$('#confirmDeleteModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget) // Button that triggered the modal
	var warehouseId = button.data('warehouse-id') // Extract info from data-* attributes
	$('#warehouseId').val(warehouseId)

})

