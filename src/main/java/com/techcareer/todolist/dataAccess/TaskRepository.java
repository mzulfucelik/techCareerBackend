package com.techcareer.todolist.dataAccess;

import com.techcareer.todolist.dtos.responses.tasks.TaskDetailResponseDto;
import com.techcareer.todolist.entities.Task;
import com.techcareer.todolist.entities.enums.MissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findAllByCategoryName(String categoryName);

    @Query("SELECT new com.techcareer.todolist.dtos.responses.tasks.TaskDetailResponseDto(t.id,t.title,t.description,c.name,c.description,t.startDate,t.endDate,t.priority,t.missionStatus)"+
             "from Task t inner join t.category c ")
    List<TaskDetailResponseDto> getDetails();
    List<Task> findAllByCategory_Id(Long id);
    List<Task> findAllByMissionStatus(MissionStatus status);


    @Query("SELECT t FROM Task t WHERE t.startDate>=:start And t.endDate<=:end")
    List<Task> findAllByStartDateBetween(Date start, Date end);
    int countByTitle(String title);

    int countByCategory_Id(Long id);
    
}
