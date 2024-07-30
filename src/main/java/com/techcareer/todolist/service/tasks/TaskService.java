package com.techcareer.todolist.service.tasks;

import com.techcareer.todolist.dtos.requests.tasks.TaskAddRequestsDto;
import com.techcareer.todolist.entities.Task;
import com.techcareer.todolist.exceptions.NotFoundException;

import java.util.List;

public sealed interface TaskService permits TaskManager {

    List<Task> getAllByCategoryName(String categoryName);
    String add(TaskAddRequestsDto dto);

    Task getById(Long id) throws NotFoundException;

    List<Task> getAll();

    void delete(Long id);

    String update(Task task);
}
