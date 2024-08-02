package com.techcareer.todolist.service.tasks;

import com.techcareer.todolist.dataAccess.TaskRepository;
import com.techcareer.todolist.dtos.requests.tasks.TaskAddRequestsDto;
import com.techcareer.todolist.dtos.responses.tasks.TaskResponseDto;
import com.techcareer.todolist.entities.Category;
import com.techcareer.todolist.entities.Task;
import com.techcareer.todolist.entities.enums.MissionStatus;
import com.techcareer.todolist.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public final class TaskManager implements TaskService {

    private final TaskRepository taskRepository;

    public TaskManager(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskResponseDto> getAllByCategoryName(String categoryName) {
       List<Task> tasks = this.taskRepository.findAllByCategoryName(categoryName);

       return convertToDtoList(tasks);
    }

    @Override
    public String add(TaskAddRequestsDto dto) {

        Task task = convertToEntity(dto);
        this.taskRepository.save(task);
        return "Görev kaydedildi";

    }

    @Override
    public TaskResponseDto getById(Long id) {

        Task task = taskRepository.findById(id).orElseThrow(()-> new NotFoundException(id,"Görev"));

        return convertToDto(task);
    }

    @Override
    public List<TaskResponseDto> getAll() {

        List<Task> tasks = this.taskRepository.findAll();

        return convertToDtoList(tasks);

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

    private TaskResponseDto convertToDto(Task task){

        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStartDate(),
                task.getEndDate(),
                task.getCreateDate(),
                task.getPriority(),
                task.getMissionStatus()
        );
    }

    private List<TaskResponseDto> convertToDtoList(List<Task> tasks){

        List<TaskResponseDto> responseDtos = new ArrayList<>();
        for (Task task  : tasks){
            TaskResponseDto responseDto = convertToDto(task);
            responseDtos.add(responseDto);
        }

        return responseDtos;
    }
}
