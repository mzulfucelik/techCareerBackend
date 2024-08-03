package com.techcareer.todolist.service.categories;

import com.techcareer.todolist.dataAccess.CategoryRepository;
import com.techcareer.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.techcareer.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.techcareer.todolist.dtos.responses.categories.CategoryResponseDto;
import com.techcareer.todolist.entities.Category;
import com.techcareer.todolist.exceptions.BusinessException;
import com.techcareer.todolist.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class CategoryManager implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final BaseCategoryMapper mapper;

    public CategoryManager(CategoryRepository categoryRepository, @Qualifier("Automatic") BaseCategoryMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(()->new NotFoundException(id,"Kategori"));

        CategoryResponseDto response = mapper.convertToDto(category);
        return response;

    }

    @Override
    public String delete(Long id) {

        Category category = this.categoryRepository.findById(id).orElseThrow(
                ()->new NotFoundException(id,"Kategori"));
        this.categoryRepository.delete(category);

        return "Kategori silindi.";

    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {

        /*List<CategoryResponseDto> responseDtos = new ArrayList<>();

        List<Category> list = this.categoryRepository.findAll();

        for (Category category : list){
            CategoryResponseDto categoryResponseDto = convertToDto(category);
            responseDtos.add(categoryResponseDto);
        }

        return responseDtos;*/

        return this.categoryRepository.findAll()
                .stream()
                .map(mapper ::convertToDto)
                .toList();

    }

    @Override
    public String add(CategoryAddRequestDto dto) {

       categoryNameMustUnique(dto.name());

        Category category = mapper.convertToEntity(dto);
        this.categoryRepository.save(category);

        return "Kategori eklendi.";

    }

    @Override
    public String update(CategoryUpdateRequestDto dto) {

        boolean isPresent = this.categoryRepository.existsById(dto.id());

        if (isPresent){

            Category category = mapper.convertToEntity(dto);
            this.categoryRepository.save(category);
            return "Kategori güncellendi.";

        }
        else {


            throw  new NotFoundException(dto.id(), "Kategori");

        }

    }

    private void categoryNameMustUnique(String name){
        int count = this.categoryRepository.countByName(name);
        if (count>0){
            throw new BusinessException("Bu isimde bir kategori zaten var :"+name);
        }
    }




}
