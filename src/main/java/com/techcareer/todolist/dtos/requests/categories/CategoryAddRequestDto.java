package com.techcareer.todolist.dtos.requests.categories;

import com.techcareer.todolist.aop.annotations.CategoryNameMustBeUnique;

public record CategoryAddRequestDto(
        @CategoryNameMustBeUnique
        String name,
        String description
) {


}
