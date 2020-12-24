function initPage() {
    var jobid = getUrlParameter("id");
    checkTimetable(jobid);
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
        content += '<br>Prof. ' + lesson.teacher;
        content += '<br>' + lesson.room.name;
        content += '</div>';
        $(elem_id).html(content);
    });
    $('#timetable_container').fadeIn();
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