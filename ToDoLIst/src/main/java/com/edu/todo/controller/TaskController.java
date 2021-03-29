package com.edu.todo.controller;

import com.edu.todo.entity.Task;
import com.edu.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping(value = "/api/tasks")
    public ResponseEntity<?> create (@RequestBody Task task){
        taskService.create(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/tasks")
    public ResponseEntity<List<Task>> findAll(){
        final List<Task> taskList = taskService.findAll();

        return taskList != null && !taskList.isEmpty()
                ? new ResponseEntity<>(taskList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
