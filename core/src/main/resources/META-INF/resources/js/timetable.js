var STORE_ORIGIN = window.location.origin;

function displayTimetableList(list) {
    $("#tbl_body_timetable").empty();
    $.each(list, function (index, info) {
        addTimetableInfoToTable(info);
    });
};

function addTimetableInfoToTable(info) {
    var rowContent = '<tr>';
    rowContent += '<td style="text-align: left">' + info.name + '</td>';
    rowContent += '<td style="text-align: left">' + info.description + '</td>';
    rowContent += '<td style="cursor: pointer;" class="view_timetable" data-id="' + info.id + '"><img src="../images/file-text.svg" alt="view" width="32" height="32"></a></td>';
    rowContent += '<td style="cursor: pointer;" class="setup_timetable" data-id="' + info.id + '"><img src="../images/pencil-square.svg" alt="edit" width="32" height="32"></a></td>';
    rowContent += '<td style="cursor: pointer;" class="delete_timetable" data-id="' + info.id + '"><img src="../images/trash.svg" alt="delete" width="32" height="32"></td>';
    rowContent += '</tr>';
    $('#tbl_timetable  tbody').append(rowContent);
};

// --- STORAGE FUNCTIONS ----------------------------------------- 
function saveTimetable(callbackFunction) {
    var theUrl = STORE_ORIGIN + '/timetables';
    var tt = {};
    tt.name = $('#inputTimetableName').val();
    tt.description = $('#inputTimetableDescription').val();
    $.ajax({
        type: "POST",
        url: theUrl,
        data: JSON.stringify(tt),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        complete: function(response, status, xhr){
            var timetable = jQuery.parseJSON(response.responseText);
            callbackFunction(timetable);
        }
    });
};

function loadTimetable(id, callbackFunction) {
    var theUrl = STORE_ORIGIN + '/timetables/' + id;
    $.ajax({
        url: theUrl,
        type: 'GET',
        dataType: 'json',
        complete: function(response, status, xhr){
            callbackFunction(jQuery.parseJSON(response.responseText));
        }
    });
};

function loadTimetables(callbackFunction) {
    var theUrl = STORE_ORIGIN + '/timetables';
    $.ajax({
        url: theUrl,
        type: 'GET',
        dataType: 'json',
        complete: function(response, status, xhr){
            callbackFunction(jQuery.parseJSON(response.responseText));
        }
    });
};

function deleteTimetable(id) {
    var theUrl = STORE_ORIGIN + '/timetables/' + id;
    $.ajax({
        url: theUrl,
        type: 'DELETE',
        dataType: 'json',
        complete: function(response, status, xhr){
            console.log('TIMETABLE ' + id + ' deleted!');
        }
    });
};