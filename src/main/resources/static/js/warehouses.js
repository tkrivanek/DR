//setting disabled on endDate field
$(document).ready(function() {
	document.getElementById("endDate").disabled = true;
});

// datepicker for fromDate input
$("#fromDate").datepicker({
	format : "dd/mm/yyyy",
	language : "hr",
	todayHighlight : true,
	startDate : "today",
	autoclose : true
});

// datepicker for endDate input
$("#endDate").datepicker({
	format : "dd/mm/yyyy",
	language : "hr",
	todayHighlight : true,
	startDate : "today",
	autoclose : true
});

function checkDates() {
	var date1 = $('#fromDate').datepicker('getDate');
	var date2 = $('#endDate').datepicker('getDate');
	if (date1 && date2) {
		var time1 = date1.getTime();
		var time2 = date2.getTime();
		if (time1 >= time2) {
			$('#notification').removeClass('d-none');
			$('#div').removeClass('d-none');
			$("#div").fadeTo(5000, 500).slideUp(500, function() {
				$("#div").slideUp(500);
				$("#div").addClass('d-none');
			});
			$("#endDate").datepicker("hide");
			$("#fromDate").datepicker("hide");
			$('#fromDate').datepicker('clearDates');
			$('#numberOfDays').text('Unajmljujete skladište na 0 dana.');
			$('#price').text('Ukupna cijena iznosi 0 HRK');
			return false;
		} else {
			return true;
		}
	}
};
// setting disabled true or false on dependent fields
$('#fromDate').on('changeDate', function() {
	if (document.getElementById("fromDate").value !== '') {
		document.getElementById("endDate").disabled = false;
	} else {
		document.getElementById("endDate").disabled = true;
		document.getElementById("endDate").value = "";
	}
	if (document.getElementById("endDate").value !== '') {
		checkDates();
	}

});

// calling fuction on change of endDate input
$('#endDate').on('change', function() {
	if (checkDates()) {
		calcDiff();
	}
});

// calculating days between dates and total price of rent
function calcDiff() {
	var date1 = $('#fromDate').datepicker('getDate');
	var date2 = $('#endDate').datepicker('getDate');
	var diff = 0;
	var priceAll = $('#dailyPrice').text();
	var price = priceAll.substring(0, priceAll.length -4)
	var totPrice = 0;
	if (date1 && date2) {
		diff = Math.floor((date2.getTime() - date1.getTime()) / 86400000);
		if (price) {
			totPrice = price * diff;
		}
	}
	$('#numberOfDays').text('Unajmljujete skladište na ' + diff + ' dana.');
	$('#price').text('Ukupna cijena iznosi ' + totPrice + ' HRK');
}

// setting warehouse attributes for canceling rent
$('#confirmCancelRentModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget) // Button that triggered the modal
	var warehouseId = button.data('warehouse-id') // Extract info from data-*
	// attributes
	var userId = button.data('user-id') // Extract info from data-* attributes
	$('#warehouseId').val(warehouseId)
	$('#userId').val(userId)

});

$('#confirmDeleteModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget) // Button that triggered the modal
	var warehouseId = button.data('warehouse-id') // Extract info from data-*
	// attributes
	$('#warehouseId').val(warehouseId)

});

$('#closeAlert').on('click', function(event) {
	$("#div").addClass('d-none');
});
