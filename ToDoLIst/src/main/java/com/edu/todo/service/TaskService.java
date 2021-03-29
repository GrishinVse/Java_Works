package com.edu.todo.service;

import com.edu.todo.entity.Task;
import com.edu.todo.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;

    public void create(Task task){
        taskRepo.save(task);
    }

    public void update(Task task) { taskRepo.save(task); }

    public void delete(Task task) { taskRepo.delete(task); }

    public List<Task> findAll(){
        return taskRepo.findAll();
    }

    public Optional<Task> find(Long id){
        return taskRepo.findById(id);
    }
}
