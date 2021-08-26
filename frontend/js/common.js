function checkUser(okCallback, errorCallback) {
    if (!localStorage.userid) {
        errorCallback();
    } else {
        okCallback(localStorage.userid);
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

function loadUser(callbackFunction, errorCallback) {
    var theUrl = 'rs/users/' + localStorage.getItem('userid');
    $.ajax({
        url: theUrl,
        type: 'GET',
        contentType: 'application/json',
        complete: function(response, status, xhr){
            var data = {};
            if(response.responseText.length > 0){
                data = jQuery.parseJSON(response.responseText);
                callbackFunction(data);
            } else {
                errorCallback();
            }
        }
    });
};

function loadSchool(id, callback, errorCallback) {
    var theUrl = 'rs/schools/' + id;
    $.ajax({
        url: theUrl,
        type: 'GET',
        contentType: 'application/json',
        complete: function(response, status, xhr){
            var data = {};
            if(response.responseText.length > 0){
                data = jQuery.parseJSON(response.responseText);
                callback(data);
            } else {
                errorCallback();
            }
        }
    });
};

function loadUserByCode(code, callbackFunction) {
    var theUrl = 'rs/users/code/' + code;
    $.ajax({
        url: theUrl,
        type: 'GET',
        contentType: 'application/json',
        complete: function(response, status, xhr){
            var data = {};
            if(response.responseText.length > 0){
                data = jQuery.parseJSON(response.responseText);
            } else {
                data.name = 'Nessuno (codice non valido)';
            }
            callbackFunction(data);
        }
    });
};

function loadLessonsByTeacher(teacher, callbackFunction) {
    var id = getUrlParameter("id");
    var theUrl = 'rs/orario/' + id + '?t=' + teacher;
    var res = encodeURIComponent(theUrl);
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
    var theUrl = 'rs/orario/' + id + '?r=' + classroom;
    var res = encodeURIComponent(theUrl);
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
    var theUrl = 'rs/orario/' + id + '?s=' + studentGroup;
    var res = encodeURIComponent(theUrl);
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

function loadSchoolsByAdmin(callback) {
    var theUrl = 'rs/schools/admin/' + localStorage.getItem('userid');
    $.ajax({
        url: theUrl,
        type: 'GET',
        contentType: 'application/json',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            callback(data);
        }
    });
};

function addSchool(school, callback) {
    var theUrl = 'rs/schools';
    $.ajax({
        url: theUrl,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(school),
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            callback(data);
        }
    });
}

function removeSchool(schoolId, callback) {
    var theUrl = 'rs/schools/' + schoolId;
    $.ajax({
        url: theUrl,
        type: 'DELETE',
        contentType: 'application/json',
        complete: function(response, status, xhr){
            callback(id);
        }
    });
}

function addTimetable(timetable, callback) {
    var theUrl = 'rs/timetables';
    $.ajax({
        url: theUrl,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(timetable),
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            callback(data);
        }
    });
}

function loadTimetablesBySchool(schoolId, callback) {
    var theUrl = 'rs/timetables/school/' + schoolId;
    $.ajax({
        url: theUrl,
        type: 'GET',
        contentType: 'application/json',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            callback(data);
        }
    });
};

function startEngine(id) {
    var theUrl = 'rs/engine/' + id;
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
    var theUrl = 'rs/orario/' + id;
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
    var theUrl = 'rs/orario/check/' + id;
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