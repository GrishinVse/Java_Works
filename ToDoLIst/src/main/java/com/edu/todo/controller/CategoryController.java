package com.edu.todo.controller;

import com.edu.todo.entity.Category;
import com.edu.todo.service.CategoryService;
import com.edu.todo.service.TaskService;
import com.edu.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/api/categories")
    public ResponseEntity<?> create (@RequestBody Category category){
        categoryService.create(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/categories")
    public ResponseEntity<List<Category>> findAll(){
        final List<Category> categoryList = categoryService.findAll();

        return categoryList != null && !categoryList.isEmpty()
                ? new ResponseEntity<>(categoryList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
