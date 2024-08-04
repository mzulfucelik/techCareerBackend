package com.techcareer.todolist.service.tasks;

import com.techcareer.todolist.dtos.requests.tasks.TaskAddRequestsDto;
import com.techcareer.todolist.dtos.requests.tasks.TaskUpdateRequestDto;
import com.techcareer.todolist.dtos.responses.tasks.TaskDetailResponseDto;
import com.techcareer.todolist.dtos.responses.tasks.TaskResponseDto;
import com.techcareer.todolist.entities.Task;
import com.techcareer.todolist.entities.enums.MissionStatus;
import com.techcareer.todolist.exceptions.NotFoundException;

import java.util.Date;
import java.util.List;

public sealed interface TaskService permits TaskManager {

    List<TaskResponseDto> getAllByCategoryName(String categoryName);
    String add(TaskAddRequestsDto dto);

    TaskResponseDto getById(Long id) throws NotFoundException;

    List<TaskResponseDto> getAll();

    void delete(Long id);

    String update(Task task);

    List<TaskDetailResponseDto> getAllDetails();

    List<TaskDetailResponseDto> getAllCategoryName(String categoryName);
    List<TaskDetailResponseDto> getAllCategoryId(Long id);
    List<TaskDetailResponseDto> getAllDateRange(Date startDate, Date endDate);

    List<TaskResponseDto> getAllByStatus(MissionStatus status);

    String update(TaskUpdateRequestDto dto) throws NotFoundException;
}
