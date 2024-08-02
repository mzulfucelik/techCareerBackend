package com.techcareer.todolist.api;

import com.techcareer.todolist.dtos.requests.tasks.TaskAddRequestsDto;
import com.techcareer.todolist.dtos.responses.tasks.TaskResponseDto;
import com.techcareer.todolist.entities.Task;
import com.techcareer.todolist.exceptions.NotFoundException;
import com.techcareer.todolist.service.tasks.TaskService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks/")
@Api(tags = "Görevler")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("add")
    public ResponseEntity<String > add(@RequestBody @Valid TaskAddRequestsDto dto){

        String result = taskService.add(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("getbyid/{id}")
    public ResponseEntity<TaskResponseDto> getById(@PathVariable("id") Long id){
        TaskResponseDto result = taskService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("getall")
    public ResponseEntity<List<TaskResponseDto>> getAll(){

        List<TaskResponseDto> tasks = taskService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("getallcategory")
    public ResponseEntity<List<TaskResponseDto>> getAllByCategory(@RequestParam String categoryName){
        List<TaskResponseDto> tasks = taskService.getAllByCategoryName(categoryName);
        return  ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

}
