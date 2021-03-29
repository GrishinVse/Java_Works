package com.edu.todo.service;

import com.edu.todo.entity.Category;
import com.edu.todo.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public void create(Category category){
        categoryRepo.save(category);
    }

    public void update(Category category) { categoryRepo.save(category); }

    public void delete(Category category) { categoryRepo.delete(category); }

    public List<Category> findAll(){
        return categoryRepo.findAll();
    }

    public Optional<Category> find(Long id){
        return categoryRepo.findById(id);
    }


}
