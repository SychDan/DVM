$(document).ready(function () {

    $(".button_number").click(function () {
        if ($('#input').val().length <=4) {
            $('#input').val($('#input').val() + this.id);
        }
    });

    $('#removeLastElem').click(function () {
        $('#input').val($('#input').val().slice(0,-1));
    });

});

function validate(evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode( key );
    var regex = /[0-9]|\./;
    if( !regex.test(key) ) {
        theEvent.returnValue = false;
        if(theEvent.preventDefault) theEvent.preventDefault();
    }
}