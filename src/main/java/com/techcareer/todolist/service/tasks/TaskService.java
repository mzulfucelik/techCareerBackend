package com.techcareer.todolist.service.tasks;

import com.techcareer.todolist.dtos.requests.tasks.TaskAddRequestsDto;
import com.techcareer.todolist.dtos.responses.tasks.TaskResponseDto;
import com.techcareer.todolist.entities.Task;
import com.techcareer.todolist.exceptions.NotFoundException;

import java.util.List;

public sealed interface TaskService permits TaskManager {

    List<TaskResponseDto> getAllByCategoryName(String categoryName);
    String add(TaskAddRequestsDto dto);

    TaskResponseDto getById(Long id) throws NotFoundException;

    List<TaskResponseDto> getAll();

    void delete(Long id);

    String update(Task task);
}
