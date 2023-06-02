package com.kreitek.store.infrastructure.rest;

import com.kreitek.store.application.dto.CategoryDTO;
import com.kreitek.store.application.service.service.CategoryService;
import com.kreitek.store.domain.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @CrossOrigin
    @GetMapping(value = "/categories",produces = "application/json")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(@RequestParam(name = "partialName", required = false) String partialName){
        List<CategoryDTO> categoryDTOS;
        if(partialName == null){
            categoryDTOS = this.categoryService.getAllCategories();
        }else{
            categoryDTOS = this.categoryService.getAllCategoriesByName(partialName);
        }

        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/categories",produces = "application/json",consumes = "application/json")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        categoryDTO = this.categoryService.saveCategory(categoryDTO);
        return new ResponseEntity<>(categoryDTO,HttpStatus.CREATED);
    }
}
