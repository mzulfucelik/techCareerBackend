package com.techcareer.todolist.dtos.responses.tasks;

import com.techcareer.todolist.entities.enums.MissionStatus;
import com.techcareer.todolist.entities.enums.Priority;

import java.util.Date;

public record TaskResponseDto(

        Long id,
        String  title,
        String  description,
        Date startDate,
        Date endDate,
        Date createDate,
        Priority priority,
        MissionStatus missionStatus
) {
}
