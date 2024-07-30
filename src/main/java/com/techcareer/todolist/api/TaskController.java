package com.techcareer.todolist.api;

import com.techcareer.todolist.dtos.requests.tasks.TaskAddRequestsDto;
import com.techcareer.todolist.service.tasks.TaskService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tasks/")
@Api(tags = "Görevler")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("add")
    public ResponseEntity<String > add(@RequestBody TaskAddRequestsDto dto){

        String result = taskService.add(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}
