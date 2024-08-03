package com.techcareer.todolist.service.categories;

import com.techcareer.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.techcareer.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.techcareer.todolist.dtos.responses.categories.CategoryResponseDto;
import com.techcareer.todolist.entities.Category;

public sealed interface BaseCategoryMapper permits CategoryMapper, CategoryModelMapper  {

    CategoryResponseDto convertToDto(Category category);
    Category convertToEntity(CategoryAddRequestDto dto);
    Category convertToEntity(CategoryUpdateRequestDto dto);

}
