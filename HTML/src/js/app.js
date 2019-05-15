// Dropdown
$('.dropdown-toggle').click(function (e) {
    e.preventDefault()
    // $(this).next('.dropdown-menu').toggle();
    $(this).parent('.dropdown').toggleClass('open');
});