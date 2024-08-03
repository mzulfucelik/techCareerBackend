package com.techcareer.todolist.service.tasks;

import com.techcareer.todolist.dtos.requests.tasks.TaskAddRequestsDto;
import com.techcareer.todolist.dtos.responses.tasks.TaskDetailResponseDto;
import com.techcareer.todolist.dtos.responses.tasks.TaskResponseDto;
import com.techcareer.todolist.entities.Category;
import com.techcareer.todolist.entities.Task;
import com.techcareer.todolist.entities.enums.MissionStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskMapper {

    public List<TaskDetailResponseDto> convertToDetailDtoList(List<Task> tasks){
        List<TaskDetailResponseDto> responseDtos = new ArrayList<>();

        for (Task task : tasks){
            TaskDetailResponseDto dto = convertToDetailDto(task);
            responseDtos.add(dto);
        }
        return responseDtos;
    }

    public Task convertToEntity(TaskAddRequestsDto dto){

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

    public TaskResponseDto convertToDto(Task task){

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

    public List<TaskResponseDto> convertToDtoList(List<Task> tasks){

        List<TaskResponseDto> responseDtos = new ArrayList<>();
        for (Task task  : tasks){
            TaskResponseDto responseDto = convertToDto(task);
            responseDtos.add(responseDto);
        }

        return responseDtos;
    }

    public TaskDetailResponseDto convertToDetailDto(Task task){
        return new TaskDetailResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCategory().getName(),
                task.getCategory().getDescription(),
                task.getStartDate(),
                task.getEndDate(),
                task.getPriority(),
                task.getMissionStatus()
        );
    }
}
