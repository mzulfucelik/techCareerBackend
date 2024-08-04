package com.techcareer.todolist.api;

import com.techcareer.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.techcareer.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.techcareer.todolist.dtos.responses.categories.CategoryResponseDto;
import com.techcareer.todolist.entities.Category;
import com.techcareer.todolist.service.categories.CategoryService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories/")
@Api(tags = "Kategoriler")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("add")
    public ResponseEntity<String> add(@Valid @RequestBody CategoryAddRequestDto categoryAddRequestDto){

        var result = categoryService.add(categoryAddRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @PutMapping("update")
    public ResponseEntity<String> update(@Valid @RequestBody CategoryUpdateRequestDto updateRequestDto){

        var result = categoryService.update(updateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        var result = categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("getbyid")
    private ResponseEntity<CategoryResponseDto> getById(@RequestParam long id){

        var result = categoryService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @GetMapping("getall")
    private ResponseEntity<List<CategoryResponseDto>> getAll(){

        var result = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }


}
