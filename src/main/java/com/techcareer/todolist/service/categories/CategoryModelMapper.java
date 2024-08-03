package com.techcareer.todolist.service.categories;

import com.techcareer.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.techcareer.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.techcareer.todolist.dtos.responses.categories.CategoryResponseDto;
import com.techcareer.todolist.entities.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component("Automatic")
public final class CategoryModelMapper implements BaseCategoryMapper {

    private final ModelMapper modelMapper;

    public CategoryModelMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public CategoryResponseDto convertToDto(Category category) {
        return modelMapper.map(category,CategoryResponseDto.class);
    }

    @Override
    public Category convertToEntity(CategoryAddRequestDto dto) {
        return modelMapper.map(dto,Category.class);
    }

    @Override
    public Category convertToEntity(CategoryUpdateRequestDto dto) {
        return modelMapper.map(dto,Category.class);
    }
}
