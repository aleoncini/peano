function formatSchoolsTable(schools) {
    $("#tbl_schools").html("");
    $("#sel_school").html("");
    var tableContent = '<tbody>';
    var selectionContent = '';
      $.each(schools, function (index, school) {
            tableContent += '<tr class="goto_view_school" data-id="' + school.id + '">';
            tableContent += '<td class=\"nm\">' + school.name + '</td>';
            tableContent += '</tr>';
            selectionContent += '<option value="' + school.id + '">' + school.name +'</option>';
            loadTimetablesBySchool(school.id, formatTimetablesTable);
        });
      tableContent += '</tbody>';
      $("#tbl_schools").html(tableContent);
      $("#sel_school").html(selectionContent);
};

function formatTimetablesTable(timetables) {
    $.each(timetables, function (index, tt) {
        addTimetableToTable(tt);
    });
};

function addTimetableToTable(timetable) {
    var status = timetable.solverStatus;
    if(status == null){
        status = 'mai calcolato';
    }
    var tableContent;
    tableContent += '<tr class="goto_view_timetable" data-id="' + timetable.id + '">';
    tableContent += '<td class=\"nm\">' + timetable.description + '</td>';
    tableContent += '<td class=\"subj\">' + status + '</td>';
    tableContent += '</tr>';
    $("#tbl_timetables tbody").prepend(tableContent);
};