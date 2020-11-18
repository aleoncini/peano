var demouuid = "2d71acaa-ddd9-4a38-aa02-34cfc51fb6a1";
var BASE_URL = 'http://ersamurai:8080/';

function startEngine(id) {
    var theUrl = BASE_URL + 'engine/' + id;
    $.ajax({
        url: theUrl,
        type: 'POST',
        contentType: 'application/json',
        complete: function(response, status, xhr){
            window.location.href = "orario.html?id=" + demouuid;
        }
    });
}

function deleteTimetable(id, callbackFunction) {
    var theUrl = BASE_URL + 'orario/' + id;
    $.ajax({
        url: theUrl,
        type: 'DELETE',
        contentType: 'application/json',
        complete: function(response, status, xhr){
            callbackFunction(id);
        }
    });
};