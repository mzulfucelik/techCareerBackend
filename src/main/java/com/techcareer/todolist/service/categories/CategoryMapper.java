package com.techcareer.todolist.service.categories;

import com.techcareer.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.techcareer.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.techcareer.todolist.dtos.responses.categories.CategoryResponseDto;
import com.techcareer.todolist.entities.Category;
import org.springframework.stereotype.Component;

@Component("Manuel")
public final class CategoryMapper implements BaseCategoryMapper {

    public CategoryResponseDto convertToDto(Category category){

        return new CategoryResponseDto(category.getId(),category.getName(),category.getDescription());
    }

    public Category convertToEntity(CategoryAddRequestDto dto){

        Category category = new Category();
        category.setName(dto.name());
        category.setDescription(dto.description());

        return category;
    }


    public Category convertToEntity(CategoryUpdateRequestDto dto){

        Category category = new Category();
        category.setId(dto.id());
        category.setName(dto.name());
        category.setDescription(dto.description());

        return category;
    }
}
