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
        $('#showModal').removeAttr('disabled');
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

    $('#showModalWin').click(function (event) {
        var button = $(event.relatedTarget)
        var recipient = button.data('whatever')
        var modal = $(this)
        modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('.modal-body input').val(recipient)
        //I would prevent the default behaviour of the button
        event.preventDefault();
        var div = document.createElement('div');
        div.innerHTML = "<img alt='image' src='/persons/" + id + "/showData' width=100% height=100%>";
        document.getElementById("areaValue").removeChild(document.getElementById("areaValue").firstChild);
        document.getElementById("areaValue").appendChild(div);
        $('#exampleModal').modal('show');
    })
});