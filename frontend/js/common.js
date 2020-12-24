var BASE_URL = 'https://sec-fe-peano.apps.ocp4.rhocplab.com/';
//var BASE_URL = 'http://ersamurai:8082/rs/';

function checkUser() {
    if (!localStorage.userid) {
        $('#enterUserInfoModal').modal('show');
    }
};

function checkSchool() {
    var school = JSON.parse(localStorage.getItem('school'));
    if (school != null) {
        loadTeachers(formatTeachersTable);
    } else {
        window.location.href = "school.html";
    }
};

function uuidv4() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
      var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
      return v.toString(16);
    });
};

function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};

function loadLessonsByTeacher(teacher, callbackFunction) {
    var id = getUrlParameter("id");
    var theUrl = BASE_URL + 'orario/' + id + '?t=' + teacher;
    var res = encodeURIComponent(theUrl);
    console.log("===> " + res);
    $.ajax({
        url: theUrl,
        type: 'GET',
        contentType: 'application/json',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            callbackFunction(data);
        }
    });
};

function loadLessonsByClassroom(classroom, callbackFunction) {
    var id = getUrlParameter("id");
    var theUrl = BASE_URL + 'orario/' + id + '?r=' + classroom;
    var res = encodeURIComponent(theUrl);
    console.log("===> " + res);
    $.ajax({
        url: theUrl,
        type: 'GET',
        contentType: 'application/json',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            callbackFunction(data);
        }
    });
};

function loadLessonsByStudentGroup(studentGroup, callbackFunction) {
    var id = getUrlParameter("id");
    var theUrl = BASE_URL + 'orario/' + id + '?s=' + studentGroup;
    var res = encodeURIComponent(theUrl);
    console.log("===> " + res);
    $.ajax({
        url: theUrl,
        type: 'GET',
        contentType: 'application/json',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            callbackFunction(data);
        }
    });
};

function startEngine(id) {
    var theUrl = BASE_URL + 'engine/' + id;
    $.ajax({
        url: theUrl,
        type: 'POST',
        contentType: 'application/json',
        complete: function(response, status, xhr){
            window.location.href = "orario-demo.html?id=" + id;
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

function checkTimetable(id) {
    var theUrl = BASE_URL + 'orario/check/' + id;
    $.ajax({
        url: theUrl,
        type: 'GET',
        contentType: 'application/json',
        success: function(response, status, xhr){
            handleTimetableReady(id);
        },
        error: function(response, status, xhr){
            handleTimetableStatus(id, status);
        }
    });
};

function handleTimetableReady(id) {
    $('#wait_for_solution').hide();
    $('#selection_container').fadeIn();
};

async function handleTimetableStatus(id) {
    $('#wait_for_solution').fadeIn();
    await sleep(5000);
    checkTimetable(id);
};