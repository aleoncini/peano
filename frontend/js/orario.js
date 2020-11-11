var BASE_URL = 'http://localhost:8080/';
var timetableId = "2d71acaa-ddd9-4a38-aa02-34cfc51fb6a1";
var schoolId = '5fa138e549dfb3553d32d318';

function uuidv4() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
      var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
      return v.toString(16);
    });
};

function initPage() {
    var jobid = getUrlParameter("id");
    console.log("===> id: " + jobid);
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

function loadLessonsByTeacher(teacher, callbackFunction) {
};