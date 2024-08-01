package com.techcareer.todolist.dtos.requests.tasks;

import com.techcareer.todolist.entities.enums.MissionStatus;
import com.techcareer.todolist.entities.enums.Priority;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record TaskUpdateRequestDto(

        Long id,
        Long categoryId,
        @NotEmpty(message = "Başlık alanı boş olamaz.")
        @Size(min = 2, message = "Başlık alanı minimum 2 karakterli olmalıdır.")
        String title,
        String description,
        Date startDate,
        Date endDate,
        Priority priority,
        MissionStatus missionStatus

) {
}
