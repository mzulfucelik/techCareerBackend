package com.techcareer.todolist.dataAccess;

import com.techcareer.todolist.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findAllByCategoryName(String categoryName);
    
}
