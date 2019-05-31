$(document).ready(function () {

    var id;
    var url;
    var type;

    $('#myTable').on('click', '.clickable-row', function(event) {
        url = window.location.pathname;
        type = "auth";
        id = $(this).closest('tr').find('td:first').text();
        $(this).addClass('table-active').siblings().removeClass('table-active');
        $('#showModal').removeAttr('disabled');
        $('#more').removeAttr('disabled');
    });

    $('#videoTable').on('click', '.clickable-row', function(event) {
        url = window.location.pathname;
        type="video";
        id = $(this).closest('tr').find('td:first').text()
        $(this).addClass('table-active').siblings().removeClass('table-active');
        $('#showModal').removeAttr('disabled');
    });

    $('#photoTable').on('click', '.clickable-row', function(event) {
        url = window.location.pathname;
        type = "photo";
        id = $(this).closest('tr').find('td:first').text();
        $(this).addClass('table-active').siblings().removeClass('table-active');
        $('#showModal').removeAttr('disabled');
    });

    $('#logTable').on('click', '.clickable-row', function(event) {
        url = window.location.pathname;
        type = "log";
        id = $(this).closest('tr').find('td:first').text();
        $(this).addClass('table-active').siblings().removeClass('table-active');
        $('#showModal').removeAttr('disabled');
    });



    $('#showModal').bind('click', function (event) {
        var button = $(event.relatedTarget)
        var recipient = button.data('whatever')
        var modal = $(this)
        modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('.modal-body input').val(recipient)
        //I would prevent the default behaviour of the button
        event.preventDefault();
        var div = document.createElement('div');
        if (type === "auth") {
            div.innerHTML = "<video id=\'video_telechargeable\' width=\'360\' height=\'250\' controls><source src='/journals/" + id + "/video\' type=\'video/mp4\'></source></video>";
            document.getElementById("areaValue").removeChild(document.getElementById("areaValue").firstChild);
            document.getElementById("areaValue").appendChild(div);
        } else if (type === "video") {
            div.innerHTML = "<video id=\'video_telechargeable\' width=\'360\' height=\'250\' controls><source src='" + url + "/" + id + "/photo\' type=\'video/mp4\'></source></video>";
            document.getElementById("areaValue").removeChild(document.getElementById("areaValue").firstChild);
            document.getElementById("areaValue").appendChild(div);
        } else if (type === "photo") {
            div.innerHTML = "<img alt='image' src='" + url + "/" + id + "/photo' width=100% height=100%>";
            document.getElementById("areaValue").removeChild(document.getElementById("areaValue").firstChild);
            document.getElementById("areaValue").appendChild(div);
        } else if (type === "log") {
            $.ajax({
                type : 'POST',
                url : url + "/" + id + "/log",
                success : function(data) {
                    //Get Area name after AJAX call
                    var nomeArea = data;
                    //Valorize HTML
                    $("#areaValue").html(nomeArea);
                    //Finally open popup
                }
            });
        }
        //Finally open popup
        $('#exampleModal').modal('show');
    })

    $('#more').click(function () {
        window.location.replace(url + '/' + id+'/showData')
    })
});