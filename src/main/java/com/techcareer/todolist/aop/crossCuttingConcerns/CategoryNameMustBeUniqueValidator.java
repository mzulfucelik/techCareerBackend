package com.techcareer.todolist.aop.crossCuttingConcerns;

import com.techcareer.todolist.aop.annotations.CategoryNameMustBeUnique;
import com.techcareer.todolist.dataAccess.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoryNameMustBeUniqueValidator implements ConstraintValidator<CategoryNameMustBeUnique,String> {

    private final CategoryRepository categoryRepository;

    public CategoryNameMustBeUniqueValidator(CategoryRepository categoryRepository){

        this.categoryRepository = categoryRepository;

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        int count = this.categoryRepository.countByName(value);
        return count <= 0;
    }
}
