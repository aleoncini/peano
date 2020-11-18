var BASE_URL = 'http://ersamurai:8080/';
var timetableId = "2d71acaa-ddd9-4a38-aa02-34cfc51fb6a1";
var schoolId = '5fa138e549dfb3553d32d318';

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

function initPage() {
    var jobid = getUrlParameter("id");
    checkTimetable(jobid);
};

function formatOrarioTeachersSelection(teachers) {
    var options = '<option value="none" selected>seleziona il professore</option>';
    $.each(teachers, function (index, teacher) {
        options += '<option value="' + teacher.id + '">' + teacher.name + '</option>';
    });
    $("#sel_teacher").html(options);
}  

function moveStep(step, next) {
    $('#content_step_' + step).hide();
    $('#content_step_' + next).show();
};

function checkStep() {
    var stp = localStorage.getItem("step");
    if(stp == null){
        return;
    }
    if((stp.length > 0) && (stp.length < 3)){
        moveStep(0, stp);
    }
    if(stp.length == 3){
        window.location.href = "teachers.html";
    }
};

function saveSchool(admin, schoolName, rooms) {
    var school = {};
    school.admin = admin;
    school.name = schoolName;
    school.rooms = rooms;
    addSchool(school, gotoStepTwo);
};

function saveTeacher(name, subject) {
    var school = JSON.parse(localStorage.getItem('school'));
    var teacher = {};
    teacher.schoolId = school.id;
    teacher.name = name;
    teacher.subject = subject;
    addTeacher(teacher, addTeacherToTable);
};

function gotoStepTwo(school) {
    localStorage.setItem("school", JSON.stringify(school));
    window.location.href = "teachers.html";
};

function formatTeachersTable(teachers) {
    $("#tbl_teachers").html("");
    var tableContent = '<thead>';
      tableContent += '<tr>';
      tableContent += '<th width=\"30%\">Nome docente</th>';
      tableContent += '<th width=\"70%\">Materia</th>';
      tableContent += '</tr>';
      tableContent += '</thead>';
      tableContent += '<tbody>';
      $.each(teachers, function (index, teacher) {
            tableContent += '<tr class="goto_view_teacher" data-id="' + teacher.id + '">';
            tableContent += '<td class=\"nm\" width=\"30%\">' + teacher.name + '</td>';
            tableContent += '<td class=\"subj\" width=\"70%\">' + teacher.subject + '</td>';
            tableContent += '</tr>';
      });
      tableContent += '</tbody>';
      $("#tbl_teachers").html(tableContent);
};

function addTeacherToTable(teacher) {
    var tableContent;
    tableContent += '<tr class="goto_view_teacher" data-id="' + teacher.id + '">';
    tableContent += '<td class=\"nm\" width=\"30%\">' + teacher.name + '</td>';
    tableContent += '<td class=\"subj\" width=\"70%\">' + teacher.subject + '</td>';
    tableContent += '</tr>';
    $("#tbl_teachers tbody").prepend(tableContent);
};

function addSchool(school, callbackFunction) {
    var theUrl = BASE_URL + 'schools';
    $.ajax({
        url: theUrl,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(school),
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            callbackFunction(data);
        }
    });
};

function loadTeachers(callbackFunction) {
    var school = JSON.parse(localStorage.getItem('school'));
    var theUrl =  BASE_URL + 'teachers/school/' + school.id; 
    $.ajax({
        url: theUrl,
        type: 'GET',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            callbackFunction(data);
        }
    });
};

function addTeacher(teacher, callbackFunction) {
    var theUrl = BASE_URL + 'teachers';
    $.ajax({
        url: theUrl,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(teacher),
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            callbackFunction(data);
        }
    });
};

function formatTeacherTimetable(lessons) {
    $('#timetable_container').hide();
    clearTimetable();
    $.each(lessons, function (index, lesson) {
        const color = pickColor(lesson.studentGroup);
        var elem_id = '#' + lesson.timeslot.dayOfWeek + '_' + lesson.timeslot.slot;
        var content = '<div class="ttt" style="background-color: ' + color + ';">';
        content += '<strong>' + lesson.studentGroup + '</strong>';
        content += '<br>' + lesson.room.name;
        content += '</div>';
        $(elem_id).html(content);
    });
    $('#timetable_container').fadeIn();
};

function formatClassroomTimetable(lessons) {
    $('#timetable_container').hide();
    clearTimetable();
    $.each(lessons, function (index, lesson) {
        const color = pickColor(lesson.subject);
        var elem_id = '#' + lesson.timeslot.dayOfWeek + '_' + lesson.timeslot.slot;
        var content = '<div class="ttt" style="background-color: ' + color + ';">';
        content += '<strong>' + lesson.subject + '</strong>';
        content += '<br>' + lesson.teacher;
        content += '</div>';
        $(elem_id).html(content);
    });
    $('#timetable_container').fadeIn();
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

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
};

function clearTimetable() {
    var days = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"];
    var elem_id = '';
    $.each(days, function (index, day) {
        for(i = 1; i <= 7; i++){
            elem_id = day + '_' + i;
            $(elem_id).html('');
        }         
    });
};

// ****************************************************************************
// TangoColorFactory
// ****************************************************************************

const SEQUENCE_1 = [0x8AE234, 0xFCE94F, 0x729FCF, 0xE9B96E, 0xAD7FA8];
const SEQUENCE_2 = [0x73D216, 0xEDD400, 0x3465A4, 0xC17D11, 0x75507B];

var colorMap = new Map;
var nextColorCount = 0;

function pickColor(object) {
    let color = colorMap[object];
    if (color !== undefined) {
        return color;
    }
    color = nextColor();
    colorMap[object] = color;
    return color;
}

function nextColor() {
    let color;
    let colorIndex = nextColorCount % SEQUENCE_1.length;
    let shadeIndex = Math.floor(nextColorCount / SEQUENCE_1.length);
    if (shadeIndex === 0) {
        color = SEQUENCE_1[colorIndex];
    } else if (shadeIndex === 1) {
        color = SEQUENCE_2[colorIndex];
    } else {
        shadeIndex -= 3;
        let floorColor = SEQUENCE_2[colorIndex];
        let ceilColor = SEQUENCE_1[colorIndex];
        let base = Math.floor((shadeIndex / 2) + 1);
        let divisor = 2;
        while (base >= divisor) {
            divisor *= 2;
        }
        base = (base * 2) - divisor + 1;
        let shadePercentage = base / divisor;
        color = buildPercentageColor(floorColor, ceilColor, shadePercentage);
    }
    nextColorCount++;
    return "#" + color.toString(16);
}

function buildPercentageColor(floorColor, ceilColor, shadePercentage) {
    let red = (floorColor & 0xFF0000) + Math.floor(shadePercentage * ((ceilColor & 0xFF0000) - (floorColor & 0xFF0000))) & 0xFF0000;
    let green = (floorColor & 0x00FF00) + Math.floor(shadePercentage * ((ceilColor & 0x00FF00) - (floorColor & 0x00FF00))) & 0x00FF00;
    let blue = (floorColor & 0x0000FF) + Math.floor(shadePercentage * ((ceilColor & 0x0000FF) - (floorColor & 0x0000FF))) & 0x0000FF;
    return red | green | blue;
}