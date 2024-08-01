package com.techcareer.todolist.service.tasks;

import com.techcareer.todolist.dataAccess.TaskRepository;
import com.techcareer.todolist.dtos.requests.tasks.TaskAddRequestsDto;
import com.techcareer.todolist.entities.Category;
import com.techcareer.todolist.entities.Task;
import com.techcareer.todolist.entities.enums.MissionStatus;
import com.techcareer.todolist.exceptions.NotFoundException;
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
    public List<Task> getAllByCategoryName(String categoryName) {
       return this.taskRepository.findAllByCategoryName(categoryName);
    }

    @Override
    public String add(TaskAddRequestsDto dto) {

        Task task = convertToEntity(dto);
        this.taskRepository.save(task);
        return "Görev kaydedildi";

    }

    @Override
    public Task getById(Long id) {

        Task task = taskRepository.findById(id).orElseThrow(()-> new NotFoundException(id,"Görev"));

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

    private Task convertToEntity(TaskAddRequestsDto dto){

        Task task = new Task();

        Category category = new Category();
        category.setId(dto.categoryId());

        task.setCategory(category);
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStartDate(dto.startDate());
        task.setEndDate(dto.endDate());
        task.setPriority(dto.priority());
        task.setCreateDate(new Date());
        task.setMissionStatus(MissionStatus.IN_PROCESS);

        return task;

    }
}
