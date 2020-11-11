package org.peano.orario.service;

import java.util.UUID;

import org.peano.orario.domain.Lesson;
import org.peano.orario.domain.TimeTable;

public class JobConsumer {
    
    private UUID id;

    public JobConsumer setId(UUID id){
        this.id = id;
        return this;
    }

    public void save(TimeTable timeTable) {
        for (Lesson lesson : timeTable.getLessonList()) {
            lesson.setTimetableId(id.toString()).persist();
        }
    } 

}
