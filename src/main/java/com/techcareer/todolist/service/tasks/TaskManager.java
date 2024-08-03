package com.techcareer.todolist.service.tasks;

import com.techcareer.todolist.dataAccess.TaskRepository;
import com.techcareer.todolist.dtos.requests.tasks.TaskAddRequestsDto;
import com.techcareer.todolist.dtos.responses.tasks.TaskDetailResponseDto;
import com.techcareer.todolist.dtos.responses.tasks.TaskResponseDto;
import com.techcareer.todolist.entities.Task;
import com.techcareer.todolist.exceptions.BusinessException;
import com.techcareer.todolist.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public final class TaskManager implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper mapper;

    public TaskManager(TaskRepository taskRepository,TaskMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TaskResponseDto> getAllByCategoryName(String categoryName) {
       List<Task> tasks = this.taskRepository.findAllByCategoryName(categoryName);

       return mapper.convertToDtoList(tasks);
    }

    @Override
    public String add(TaskAddRequestsDto dto) {

        titleMustBeUnique(dto.title());
        taskCategoryRule(dto.categoryId());

        Task task = mapper.convertToEntity(dto);
        this.taskRepository.save(task);
        return "Görev kaydedildi";

    }

    @Override
    public TaskResponseDto getById(Long id) {

        Task task = taskRepository.findById(id).orElseThrow(()-> new NotFoundException(id,"Görev"));

        return mapper.convertToDto(task);
    }

    @Override
    public List<TaskResponseDto> getAll() {

        List<Task> tasks = this.taskRepository.findAll();

        return mapper.convertToDtoList(tasks);

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

    @Override
    public List<TaskDetailResponseDto> getAllDetails() {
        List<Task> tasks = this.taskRepository.findAll();
        return mapper.convertToDetailDtoList(tasks);
    }

    @Override
    public List<TaskDetailResponseDto> getAllCategoryName(String categoryName) {

        List<Task> tasks = this.taskRepository.findAllByCategoryName(categoryName);
        return mapper.convertToDetailDtoList(tasks);

    }

    @Override
    public List<TaskDetailResponseDto> getAllCategoryId(Long id) {
        List<Task> tasks = this.taskRepository.findAllByCategory_Id(id);
        return mapper.convertToDetailDtoList(tasks);
    }

    @Override
    public List<TaskDetailResponseDto> getAllDateRange(Date startDate, Date endDate) {
        List<Task> tasks = this.taskRepository.findAllByStartDateBetween(startDate,endDate);
        return mapper.convertToDetailDtoList(tasks);
    }


    private void titleMustBeUnique(String title){

        int count = this.taskRepository.countByTitle(title);
        if (count>0){
            throw new BusinessException("Görev Başlığı benzersiz olmalıdır :"+title);
        }
    }

    private void taskCategoryRule(Long categoryId){
        int count = this.taskRepository.countByCategory_Id(categoryId);
        if (count>0){
            throw new BusinessException("Bir kategoriye maksimum 3 adet görev eklenebilir.");
        }
    }


}
