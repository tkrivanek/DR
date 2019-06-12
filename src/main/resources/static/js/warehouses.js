/*$(document).ready(function () {

    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); //return New or Edit

        if (text === 'Edit') {
            $.get(href, function (country, status) {
                $('.editForm #id').val(country.id);
                $('.editForm #name').val(country.name);
                $('.editForm #capital').val(country.capital);
            });
            $('.myForm #exampleModal').modal();
        } else {
            $('.editForm #id').val('');
            $('.editForm #name').val('');
            $('.editForm #capital').val('');
            $('.editForm #exampleModal').modal();
        }
    });

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#formModal #delRef').attr('href', href);
        $('#formModal').modal();
    });
});*/

$('#editModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var recipient = button.data('type') // Extract info from data-* attributes
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var modal = $(this)
    modal.find('.modal-body input #modal-name').val(recipient)
})