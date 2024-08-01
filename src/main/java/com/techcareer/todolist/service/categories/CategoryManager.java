package com.techcareer.todolist.service.categories;

import com.techcareer.todolist.dataAccess.CategoryRepository;
import com.techcareer.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.techcareer.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.techcareer.todolist.entities.Category;

import java.util.List;

public final class CategoryManager implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getById(Long id) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public String add(CategoryAddRequestDto dto) {
        return null;
    }

    @Override
    public String update(CategoryUpdateRequestDto dto) {
        return null;
    }
}
