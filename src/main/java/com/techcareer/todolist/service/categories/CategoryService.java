package com.techcareer.todolist.service.categories;

import com.techcareer.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.techcareer.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.techcareer.todolist.dtos.responses.categories.CategoryResponseDto;
import com.techcareer.todolist.entities.Category;

import java.util.List;

public sealed interface CategoryService permits CategoryManager{

    CategoryResponseDto getById(Long id);
    String delete(Long id);
    List<CategoryResponseDto> getAllCategories();

    String add(CategoryAddRequestDto dto);
    String update(CategoryUpdateRequestDto dto);
}
