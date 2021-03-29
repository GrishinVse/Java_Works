package com.edu.todo.service;

import com.edu.todo.entity.User;
import com.edu.todo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void create(User user){
        userRepo.save(user);
    }

    public void update(User user) { userRepo.save(user); }

    public void delete(User user) { userRepo.delete(user); }

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public Optional<User> find(Long id){
        return userRepo.findById(id);
    }

}
