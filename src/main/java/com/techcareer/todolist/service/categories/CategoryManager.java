package com.techcareer.todolist.service.categories;

import com.techcareer.todolist.dataAccess.CategoryRepository;
import com.techcareer.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.techcareer.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.techcareer.todolist.entities.Category;
import com.techcareer.todolist.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class CategoryManager implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getById(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(()->new NotFoundException(id,"Kategori"));

        return category;

    }

    @Override
    public String delete(Long id) {

        Category category = this.categoryRepository.findById(id).orElseThrow(
                ()->new NotFoundException(id,"Kategori"));
        this.categoryRepository.delete(category);

        return "Kategori silindi.";

    }

    @Override
    public List<Category> getAllCategories() {

        return this.categoryRepository.findAll();

    }

    @Override
    public String add(CategoryAddRequestDto dto) {

        Category category = convertToEntity(dto);
        this.categoryRepository.save(category);

        return "Kategori eklendi.";

    }

    @Override
    public String update(CategoryUpdateRequestDto dto) {

        boolean isPresent = this.categoryRepository.existsById(dto.id());

        if (isPresent){

            Category category = convertToEntity(dto);
            this.categoryRepository.save(category);
            return "Kategori güncellendi.";

        }
        else {


            throw  new NotFoundException(dto.id(), "Kategori");

        }

    }

    private Category convertToEntity(CategoryAddRequestDto dto){

        Category category = new Category();
        category.setName(dto.name());
        category.setDescription(dto.description());

        return category;
    }


    private Category convertToEntity(CategoryUpdateRequestDto dto){

        Category category = new Category();
        category.setId(dto.id());
        category.setName(dto.name());
        category.setDescription(dto.description());

        return category;
    }
}
