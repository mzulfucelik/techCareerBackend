package com.techcareer.todolist.dtos.requests.tasks;

import com.techcareer.todolist.entities.enums.Priority;

import java.util.Date;

public record TaskAddRequestsDto(

        String categoryName,
        String title,
        String description,
        Date startDate,
        Date endDate,
        Priority priority
) {
}
