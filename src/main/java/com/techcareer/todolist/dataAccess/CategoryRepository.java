package com.techcareer.todolist.dataAccess;

import com.techcareer.todolist.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long>{

    int countByName(String name);


}
