package com.techcareer.todolist.service.categories;

import com.techcareer.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.techcareer.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.techcareer.todolist.entities.Category;

import java.util.List;

public sealed interface CategoryService permits CategoryManager{

    Category getById(Long id);
    String delete(Long id);
    List<Category> getAllCategories();

    String add(CategoryAddRequestDto dto);
    String update(CategoryUpdateRequestDto dto);
}
