$(document).ready(function () {

    var id;
    var url;
    var idSidebar;

    $('#myTable').on('click', '.clickable-row', function(event) {
        url = window.location.pathname;
        id = $(this).closest('tr').find('td:first').text();
        $(this).addClass('table-active').siblings().removeClass('table-active');
        $('#update').removeAttr('disabled');
        $('#delete').removeAttr('disabled');
        $('#download').removeAttr('disabled');
    });

    $('#myTableSidebar').on('click', '.clickable-row', function(event) {
        idSidebar = $(this).closest('tr').find('td:first').text();
        $(this).addClass('table-active').siblings().removeClass('table-active');
        $('#updateSidebar').removeAttr('disabled');
        $('#deleteSidebar').removeAttr('disabled');
        $('#downloadSidebar').removeAttr('disabled');
    });

    $('#update').click(function () {
        window.location.replace(url + '/' + id+'/update')
    });

    $('#delete').click(function () {
        window.location.replace(url + '/' +id+'/delete')
    });

    $('#download').click(function () {
        window.location.replace(url + '/' +id+'/download')
    });

    $('#updateSidebar').click(function () {
        window.location.replace('/home/' + idSidebar + '/update')
    });

    $('#deleteSidebar').click(function () {
        window.location.replace('/home/' + idSidebar + '/delete')
    });

    $('#downloadSidebar').click(function () {
        window.location.replace('/home/'  + idSidebar +'/download')
    });
});