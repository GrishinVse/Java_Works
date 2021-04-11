package com.edu.project.service;

import com.edu.project.entity.Role;
import com.edu.project.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public void create(Role role){
        roleRepo.save(role);
    }

    public void update(Role role) { roleRepo.save(role); }

    public void delete(Role role) { roleRepo.delete(role); }

    public List<Role> findAll(){
        return roleRepo.findAll();
    }

    public Optional<Role> find(Long id){
        return roleRepo.findById(id);
    }
}
