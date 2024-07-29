package com.techcareer.todolist.dataAccess;

import com.techcareer.todolist.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
    
}
