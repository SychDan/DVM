$(document).ready(function () {

    var id;
    $('#myTable').on('click', '.clickable-row', function(event) {

        id = $(this).closest('tr').find('td:first').text();
        $(this).addClass('table-active').siblings().removeClass('table-active');
        $('#update').removeAttr('disabled');
        $('#delete').removeAttr('disabled');
        $('#download').removeAttr('disabled');
    });

    $('#update').click(function () {
        window.location.replace('/persons/'+id+'/update')
    });

    $('#delelte').click(function () {
        window.location.replace('/persons/'+id+'/delete')
    });

    $('#download').click(function () {
        window.location.replace('/persons/'+id+'/download')
    });
});