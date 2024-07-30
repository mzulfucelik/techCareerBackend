package com.techcareer.todolist.service.tasks;

import com.techcareer.todolist.dataAccess.TaskRepository;
import com.techcareer.todolist.dtos.requests.tasks.TaskAddRequestsDto;
import com.techcareer.todolist.entities.Task;
import com.techcareer.todolist.entities.enums.MissionStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public final class TaskManager implements TaskService {

    private final TaskRepository taskRepository;

    public TaskManager(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public String add(TaskAddRequestsDto dto) {

        Task task = new Task();
        task.setCategoryName(dto.categoryName());
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStartDate(dto.startDate());
        task.setEndDate(dto.endDate());
        task.setPriority(dto.priority());
        task.setCreateDate(new Date());
        task.setMissionStatus(MissionStatus.IN_PROCESS);

        this.taskRepository.save(task);
        return "Görev kaydedildi";
    }

    @Override
    public Task getById(Long id) {

        Task task = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Aradığınız id ye göre görev bulunamadı."));

        return task;
    }

    @Override
    public List<Task> getAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public void delete(Long id) {

        Task task = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Aradığınız id ye göre görev bulunamadı."));

        this.taskRepository.delete(task);
    }

    @Override
    public String update(Task task) {
        taskRepository.save(task);
        return "Görev güncellendi.";
    }
}
