package com.techcareer.todolist.service.tasks;

import com.techcareer.todolist.dtos.requests.tasks.TaskAddRequestsDto;
import com.techcareer.todolist.entities.Task;

import java.util.List;

public sealed interface TaskService permits TaskManager {

    String add(TaskAddRequestsDto dto);

    Task getById(Long id);

    List<Task> getAll();

    void delete(Long id);

    String update(Task task);
}
