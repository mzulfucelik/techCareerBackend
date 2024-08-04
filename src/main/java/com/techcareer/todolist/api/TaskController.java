package com.techcareer.todolist.api;

import com.techcareer.todolist.dtos.requests.tasks.TaskAddRequestsDto;
import com.techcareer.todolist.dtos.requests.tasks.TaskUpdateRequestDto;
import com.techcareer.todolist.dtos.responses.tasks.TaskDetailResponseDto;
import com.techcareer.todolist.dtos.responses.tasks.TaskResponseDto;
import com.techcareer.todolist.entities.Task;
import com.techcareer.todolist.exceptions.NotFoundException;
import com.techcareer.todolist.service.tasks.TaskService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody TaskUpdateRequestDto dto) {
        try {
            String result = taskService.update(dto);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Görev bulunamadı.");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            taskService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Görev başarıyla silindi.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Görev bulunamadı.");
        }
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

    @GetMapping("getdetails")
    public ResponseEntity<List<TaskDetailResponseDto>>getDetails(){
        var result = this.taskService.getAllDetails();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("getallcategoryid")
    public ResponseEntity<List<TaskDetailResponseDto>> getAllByCategoryId(@RequestParam Long categoryId){
        List<TaskDetailResponseDto> tasks = taskService.getAllCategoryId(categoryId);
        return  ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("getstartandfinishrange")
    public ResponseEntity<List<TaskDetailResponseDto>>getAllDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end){
        List<TaskDetailResponseDto> tasks = taskService.getAllDateRange(start,end);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }
}
